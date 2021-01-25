package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.exception.ExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MonthConverterTest {

    @Test
    @DisplayName("Testing isValid functions")
    void isValid() {
        CronConverter converter = new MonthConverter();
        assertTrue(converter.isValid("2,5"));
        assertTrue(converter.isValid("1,5"));
        assertTrue(converter.isValid("1,12"));
        assertTrue(converter.isValid("1-5"));
        assertTrue(converter.isValid("10-12"));
        assertTrue(converter.isValid("*"));
        assertTrue(converter.isValid("1/10"));
        assertTrue(converter.isValid("*/12"));


        assertFalse(converter.isValid("12123"));
        assertFalse(converter.isValid("wrong"));
        assertFalse(converter.isValid("0,12"));
        assertFalse(converter.isValid("1-13"));
    }

    @Test
    @DisplayName("Testing Override's functions")
    void overrides() {
        CronConverter converter = new MonthConverter();

        assertEquals("month        ", converter.getKey());
        assertEquals(12, converter.getMax());
        assertEquals(1, converter.getStart());
    }

    @Test
    @DisplayName("Testing convert cron to month")
    void convert() {
        CronConverter converter = new MonthConverter();

        assertEquals(" 1 12", converter.convert("1,12"));
        assertEquals(" 1 5", converter.convert("1,5"));

        assertEquals(" 1 2 3 4 5", converter.convert("1-5"));
        assertEquals(" 2 3 4 5 6", converter.convert("2-6"));

        assertEquals(" 1 2 3 4 5 6 7 8 9 10 11 12", converter.convert("*"));
        assertEquals(" 1", converter.convert("*/12"));
        assertEquals(" 1 5 9", converter.convert("1/4"));
    }

    @Test
    @DisplayName("Testing convert exception cron to month")
    void convertException() {
        CronConverter converter = new MonthConverter();
        Exception exception = assertThrows(ExpressionException.class,
                        () -> {
                            converter.convert("1,45645");
                        });
        assertEquals("invalid argument at: month        ", exception.getMessage());

        exception = assertThrows(ExpressionException.class,
                () -> {
                    converter.convert("1-61");
                });
        assertEquals("invalid argument at: month        ", exception.getMessage());
    }
}
