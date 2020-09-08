package ru.job4j.html;

import java.util.Calendar;

public class ParseDate {
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";

    public static Calendar convertToCalendarFormat(String line) {
        Calendar calendar = Calendar.getInstance();
        String input = line.replace(",", "");
        String[] in = input.split(" ");
        if (in[0].equals(TODAY)) {
            int hour = Integer.parseInt(in[1].split(":")[0]);
            int minutes = Integer.parseInt(in[1].split(":")[1]);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            return calendar;
        }
        if (in[0].equals(YESTERDAY)) {
            int hour = Integer.parseInt(in[1].split(":")[0]);
            int minutes = Integer.parseInt(in[1].split(":")[1]);
            int month = calendar.get(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, month - 1);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            return calendar;
        }
        int day = Integer.parseInt(in[0]);
        int month = DateFactory.parseMonth(in[1]);
        int year = Integer.parseInt(in[2]);
        int hour = Integer.parseInt(in[3].split(":")[0]);
        int minutes = Integer.parseInt(in[3].split(":")[1]);
        calendar.set(year, month, day, hour, minutes);
        return calendar;
    }
}
