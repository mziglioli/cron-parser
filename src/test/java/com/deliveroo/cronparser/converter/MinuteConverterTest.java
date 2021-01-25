package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinuteConverterTest {

    @Test
    @DisplayName("Testing isValid functions")
    void isValid() {
        CronConverter converter = new MinuteCronConverter();
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("1,5"));
        assertTrue(converter.isValid("1,59"));
        assertTrue(converter.isValid("1-5"));
        assertTrue(converter.isValid("10-15"));
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("0,5"));
        assertTrue(converter.isValid("*"));
        assertTrue(converter.isValid("1/15"));
        assertTrue(converter.isValid("*/20"));


        assertFalse(converter.isValid("12123"));
        assertFalse(converter.isValid("wrong"));
        assertFalse(converter.isValid("0,99"));
        assertFalse(converter.isValid("1-999"));
    }

    @Test
    @DisplayName("Testing Override's functions")
    void overrides() {
        CronConverter converter = new MinuteCronConverter();

        assertEquals("minute       ", converter.getKey());
        assertEquals(59, converter.getMax());
    }

    @Test
    @DisplayName("Testing convert cron to minute")
    void convert() {
        CronConverter converter = new MinuteCronConverter();

        assertEquals(" 0 5", converter.convert("0,5"));
        assertEquals(" 1 5", converter.convert("1,5"));

        assertEquals(" 0 1 2 3 4 5", converter.convert("0-5"));
        assertEquals(" 1 2 3 4 5", converter.convert("1-5"));

        assertEquals(" 0 15 30 45", converter.convert("*/15"));
        assertEquals(" 0 20 40", converter.convert("*/20"));
        assertEquals(" 0 25 50", converter.convert("*/25"));
    }

    @Test
    @DisplayName("Testing convert exception cron to minute")
    void convertException() {
        CronConverter converter = new MinuteCronConverter();
        Exception exception = assertThrows(ExpressionException.class,
                        () -> {
                            converter.convert("1,45645");
                        });
        assertEquals("invalid argument at: minute       ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1-61");
                });
        assertEquals("invalid argument at: minute       ", exception.getMessage());
    }
}
