package ru.job4j.html;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParseDateTest {
    @Test
    public void whenOneDate() {
        String line = "1 сен 16, 10:56";
        Calendar date = ParseDate.convertToCalendarFormat(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        assertThat(simpleDateFormat.format(date.getTime()), is("01/09/16, 10:56"));
    }

    @Test
    public void whenOtherDate() {
        String line = "23 июл 20, 20:55";
        Calendar date = ParseDate.convertToCalendarFormat(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        assertThat(simpleDateFormat.format(date.getTime()), is("23/07/20, 20:55"));
    }

    @Test
    public void whenOtherDate2() {
        String line = "сегодня, 10:56";
        Calendar date = ParseDate.convertToCalendarFormat(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 10);
        currentDate.set(Calendar.MINUTE, 56);
        assertThat(simpleDateFormat.format(date.getTime()), is(simpleDateFormat.format(currentDate.getTime())));
    }

    @Test
    public void whenOtherDate3() {
        String line = "вчера, 10:56";
        Calendar date = ParseDate.convertToCalendarFormat(line);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.get(Calendar.DAY_OF_MONTH) - 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 10);
        currentDate.set(Calendar.MINUTE, 56);
        assertThat(simpleDateFormat.format(date.getTime()), is(simpleDateFormat.format(currentDate.getTime())));
    }
}