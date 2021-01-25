package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HourConverterTest {

    @Test
    @DisplayName("Testing isValid functions")
    void isValid() {
        CronConverter converter = new HourCronConverter();
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("1,5"));
        assertTrue(converter.isValid("1,23"));
        assertTrue(converter.isValid("1-5"));
        assertTrue(converter.isValid("10-15"));
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("*"));
        assertTrue(converter.isValid("1/15"));
        assertTrue(converter.isValid("*/20"));


        assertFalse(converter.isValid("12123"));
        assertFalse(converter.isValid("wrong"));
        assertFalse(converter.isValid("0,25"));
        assertFalse(converter.isValid("1-999"));
    }

    @Test
    @DisplayName("Testing convert cron to hour")
    void convert() {
        CronConverter converter = new HourCronConverter();

        assertEquals(" 0 5", converter.convert("0,5"));
        assertEquals(" 1 5", converter.convert("1,5"));

        assertEquals(" 0 1 2 3 4 5", converter.convert("0-5"));
        assertEquals(" 1 2 3 4 5", converter.convert("1-5"));

        assertEquals(" 0 15", converter.convert("*/15"));
        assertEquals(" 0 20", converter.convert("*/20"));
        assertEquals(" 0", converter.convert("*/25"));
        assertEquals(" 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23", converter.convert("*"));
    }

    @Test
    @DisplayName("Testing convert exception cron to hour")
    void convertException() {
        CronConverter converter = new HourCronConverter();
        Exception exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1,45645");
                });
        assertEquals("invalid argument at: hour         ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1-25");
                });
        assertEquals("invalid argument at: hour         ", exception.getMessage());
    }

    @Test
    @DisplayName("Testing Override's functions")
    void overrides() {
        CronConverter converter = new HourCronConverter();

        assertEquals("hour         ", converter.getKey());
        assertEquals(23, converter.getMax());
    }
}
