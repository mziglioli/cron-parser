package com.deliveroo.cronparser.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.deliveroo.cronparser.utils.MessageUtils.replaceSpecificValues;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MessageUtilsTest {

    @Test
    @DisplayName("Testing has range method")
    void hasRange() {
        assertTrue(MessageUtils.hasRange("0-5"));
        assertTrue(MessageUtils.hasRange("1-5"));
        assertTrue(MessageUtils.hasRange("1-60"));
        assertTrue(MessageUtils.hasRange("1-100"));

        assertFalse(MessageUtils.hasRange("0=5"));
        assertFalse(MessageUtils.hasRange("1+5"));
        assertFalse(MessageUtils.hasRange("1/60"));
        assertFalse(MessageUtils.hasRange("test"));
        assertFalse(MessageUtils.hasRange("*/60"));
        assertFalse(MessageUtils.hasRange("*"));
    }

    @Test
    @DisplayName("Testing has increment method")
    void hasIncrement() {

        assertTrue(MessageUtils.hasIncrement("1/60"));
        assertTrue(MessageUtils.hasIncrement("*/60"));

        assertFalse(MessageUtils.hasIncrement("//"));
        assertFalse(MessageUtils.hasIncrement("1-60"));
        assertFalse(MessageUtils.hasIncrement("1-100"));
        assertFalse(MessageUtils.hasIncrement("0=5"));
        assertFalse(MessageUtils.hasIncrement("1+5"));
        assertFalse(MessageUtils.hasIncrement("test"));
        assertFalse(MessageUtils.hasIncrement("*"));
    }

    @Test
    @DisplayName("Testing has specific values method")
    void hasSpecificValues() {
        assertTrue(MessageUtils.hasSpecificValues("0"));

        assertEquals(" 0", replaceSpecificValues("0"));
    }
}
