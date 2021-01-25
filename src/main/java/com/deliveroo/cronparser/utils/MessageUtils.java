package com.deliveroo.cronparser.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageUtils {

    public static final String RANGE = "-";
    public static final String INCREMENT = "/";
    public static final String STAR = "*";
    public static final String SPECIFIC = ",";

    public static boolean hasSpecificValues(String s) {
        return s.contains(SPECIFIC) || (!s.contains(RANGE) && !s.contains(STAR) && !s.contains(INCREMENT));
    }
    public static String replaceSpecificValues(String s) {
        return " " + s.replaceAll(SPECIFIC, " ");
    }
    public static boolean hasRange(String s) {
        return s.split(RANGE).length == 2;
    }
    public static boolean hasIncrement(String s) {
        return s.split(INCREMENT).length == 2;
    }
    public static boolean hasStar(String s) {
        return s.contains(STAR);
    }
    public static boolean isAll(String s) {
        return STAR.equals(s);
    }
}
