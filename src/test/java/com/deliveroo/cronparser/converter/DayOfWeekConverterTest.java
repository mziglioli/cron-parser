package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayOfWeekConverterTest {

    @Test
    @DisplayName("Testing isValid functions")
    void isValid() {
        CronConverter converter = new DayOfWeekConverter();
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("1,5"));
        assertTrue(converter.isValid("1,6"));
        assertTrue(converter.isValid("1-6"));
        assertTrue(converter.isValid("2-6"));
        assertTrue(converter.isValid("*"));
        assertTrue(converter.isValid("1/6"));
        assertTrue(converter.isValid("*/6"));


        assertFalse(converter.isValid("12123"));
        assertFalse(converter.isValid("wrong"));
        assertFalse(converter.isValid("0,7"));
        assertFalse(converter.isValid("1-8"));
    }

    @Test
    @DisplayName("Testing Override's functions")
    void overrides() {
        CronConverter converter = new DayOfWeekConverter();

        assertEquals("day of week  ", converter.getKey());
        assertEquals(6, converter.getMax());
        assertEquals(0, converter.getStart());
    }

    @Test
    @DisplayName("Testing convert cron to day of week")
    void convert() {
        CronConverter converter = new DayOfWeekConverter();

        assertEquals(" 0 6", converter.convert("0,6"));
        assertEquals(" 1 5", converter.convert("1,5"));

        assertEquals(" 1 2 3 4 5", converter.convert("1-5"));
        assertEquals(" 2 3 4 5 6", converter.convert("2-6"));

        assertEquals(" 0 1 2 3 4 5 6", converter.convert("*"));
        assertEquals(" 0 6", converter.convert("*/6"));
        assertEquals(" 1 4", converter.convert("1/3"));
    }

    @Test
    @DisplayName("Testing convert exception cron to day of week")
    void convertException() {
        CronConverter converter = new DayOfWeekConverter();
        Exception exception = assertThrows(ExpressionException.class,
                        () -> {
                            converter.convert("1,45645");
                        });
        assertEquals("invalid argument at: day of week  ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1-61");
                });
        assertEquals("invalid argument at: day of week  ", exception.getMessage());
    }
}
