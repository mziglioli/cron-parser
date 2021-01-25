package com.deliveroo.cronparser.service;

import com.deliveroo.cronparser.converter.*;
import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CronServiceTest {

    @Test
    @DisplayName("Testing parse functions")
    void parse() {
        CronService cronService = new CronService(new MinuteCronConverter(), new HourCronConverter(), new DayOfMonthConverter(), new MonthConverter(), new DayOfWeekConverter());
        String[] args = new String[]{"*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"};
        boolean pass = false;
        cronService.parse(args);
        pass = true;
        assertTrue(pass);
    }

    @Test
    @DisplayName("Testing parse exception functions")
    void parseException() {
        CronService cronService = new CronService(new MinuteCronConverter(), new HourCronConverter(), new DayOfMonthConverter(), new MonthConverter(), new DayOfWeekConverter());
        Exception exception = assertThrows(ExpressionException.class,
                () -> {
                    cronService.parse(new String[]{"*15", "0", "1,15", "*", "1-5", "/usr/bin/find"});
                });
        assertEquals("invalid argument at: minute       ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    cronService.parse(new String[]{"*/15", "wrong", "1,15", "*", "1-5", "/usr/bin/find"});
                });
        assertEquals("invalid argument at: hour         ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    cronService.parse(new String[]{"*/15", "0", "1,33", "*", "1-5", "/usr/bin/find"});
                });
        assertEquals("invalid argument at: day of month ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    cronService.parse(new String[]{"*/15", "0", "1,15", "99", "1-5", "/usr/bin/find"});
                });
        assertEquals("invalid argument at: month        ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    cronService.parse(new String[]{"*/15", "0", "1,15", "*", "1-7", "/usr/bin/find"});
                });
        assertEquals("invalid argument at: day of week  ", exception.getMessage());
    }
}
