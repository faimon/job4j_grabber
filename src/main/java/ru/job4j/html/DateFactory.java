package ru.job4j.html;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateFactory {
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

    public static int parseMonth(String line) {
        return MONTHS.get(line);
    }
}
