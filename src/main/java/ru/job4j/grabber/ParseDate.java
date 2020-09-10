package ru.job4j.grabber;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ParseDate {
    private static final String TODAY = "сегодня";
    private static final String YESTERDAY = "вчера";
    private static final Map<String, Integer> MONTHS = new HashMap<>();

    static {
        MONTHS.put("янв", Calendar.JANUARY);
        MONTHS.put("фев", Calendar.FEBRUARY);
        MONTHS.put("мар", Calendar.MARCH);
        MONTHS.put("апр", Calendar.APRIL);
        MONTHS.put("май", Calendar.MAY);
        MONTHS.put("июн", Calendar.JUNE);
        MONTHS.put("июл", Calendar.JULY);
        MONTHS.put("авг", Calendar.AUGUST);
        MONTHS.put("сен", Calendar.SEPTEMBER);
        MONTHS.put("окт", Calendar.OCTOBER);
        MONTHS.put("ноя", Calendar.NOVEMBER);
        MONTHS.put("дек", Calendar.DECEMBER);
    }

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
        int month = MONTHS.get(in[1]);
        int year = Integer.parseInt(in[2]) + 2000;
        int hour = Integer.parseInt(in[3].split(":")[0]);
        int minutes = Integer.parseInt(in[3].split(":")[1]);
        calendar.set(year, month, day, hour, minutes);
        return calendar;
    }
}
