package ru.job4j.html;

import java.util.Calendar;

public class DateFactory {
    public static int parseMonth(String line) {
        int month = -1;
        if (line.equals("янв")) {
            month = Calendar.JANUARY;
        }
        if (line.equals("фев")) {
            month = Calendar.FEBRUARY;
        }
        if (line.equals("мар")) {
            month = Calendar.MARCH;
        }
        if (line.equals("апр")) {
            month = Calendar.APRIL;
        }
        if (line.equals("май")) {
            month = Calendar.MAY;
        }
        if (line.equals("июн")) {
            month = Calendar.JUNE;
        }
        if (line.equals("июл")) {
            month = Calendar.JULY;
        }
        if (line.equals("авг")) {
            month = Calendar.AUGUST;
        }
        if (line.equals("сен")) {
            month = Calendar.SEPTEMBER;
        }
        if (line.equals("окт")) {
            month = Calendar.OCTOBER;
        }
        if (line.equals("ноя")) {
            month = Calendar.NOVEMBER;
        }
        if (line.equals("дек")) {
            month = Calendar.DECEMBER;
        }
        return month;
    }
}
