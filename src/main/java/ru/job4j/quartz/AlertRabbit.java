package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static void main(String[] args) {
        try {
            List<Long> store = new ArrayList<>();
            Properties config = new Properties();
            Connection conn = null;
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            try (InputStream in = AlertRabbit.class
                    .getClassLoader().getResourceAsStream("rabbit.properties")) {
                config.load(in);
                conn = DriverManager.getConnection(config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            data.put("connection", conn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            conn.close();
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            List<Long> store = (List<Long>) dataMap.get("store");
            Connection connection = (Connection) dataMap.get("connection");
            store.add(System.currentTimeMillis());
           try (PreparedStatement ps = connection
                   .prepareStatement("INSERT INTO rabbit (created_date) VALUES (?)")) {
               ps.setLong(1, System.currentTimeMillis());
               ps.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
        }
    }
}
