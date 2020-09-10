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
}