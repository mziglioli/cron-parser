package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayOfMonthConverterTest {

    @Test
    @DisplayName("Testing isValid functions")
    void isValid() {
        CronConverter converter = new DayOfMonthConverter();
        assertTrue(converter.isValid("1,5"));
        assertTrue(converter.isValid("1,31"));
        assertTrue(converter.isValid("1,12"));
        assertTrue(converter.isValid("1-5"));
        assertTrue(converter.isValid("10-12"));
        assertTrue(converter.isValid("*"));
        assertTrue(converter.isValid("1/12"));
        assertTrue(converter.isValid("*/31"));


        assertFalse(converter.isValid("12123"));
        assertFalse(converter.isValid("wrong"));
        assertFalse(converter.isValid("0,13"));
        assertFalse(converter.isValid("1,33"));
        assertFalse(converter.isValid("0-32"));
        assertFalse(converter.isValid("1-34"));
    }

    @Test
    @DisplayName("Testing convert cron to minute")
    void convert() {
        CronConverter converter = new DayOfMonthConverter();

        assertEquals(" 1 5", converter.convert("1,5"));
        assertEquals(" 1 12", converter.convert("1,12"));

        assertEquals(" 1 2 3 4 5", converter.convert("1-5"));
        assertEquals(" 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31", converter.convert("5-31"));

        assertEquals(" 1 11 21 31", converter.convert("*/10"));
        assertEquals(" 1 5 9 13 17 21 25 29", converter.convert("*/4"));
        assertEquals(" 1 13 25", converter.convert("*/12"));
    }

    @Test
    @DisplayName("Testing convert exception cron to day of month")
    void convertException() {
        CronConverter converter = new DayOfMonthConverter();
        Exception exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1,45645");
                });
        assertEquals("invalid argument at: day of month ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1-35");
                });
        assertEquals("invalid argument at: day of month ", exception.getMessage());
    }

    @Test
    @DisplayName("Testing Override's functions")
    void overrides() {
        CronConverter converter = new DayOfMonthConverter();

        assertEquals("day of month ", converter.getKey());
        assertEquals(31, converter.getMax());
        assertEquals(1, converter.getStart());
    }
}
