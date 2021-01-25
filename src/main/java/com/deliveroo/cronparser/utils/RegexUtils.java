package com.deliveroo.cronparser.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtils {

    public static final String REGEX_MINUTE = "^(\\*|[1-5]?[0-9](-[1-5]?[0-9])?)(\\/[1-9][0-9]*)?(,(\\*|[1-5]?[0-9](-[1-5]?[0-9])?)(\\/[1-9][0-9]*)?)*$";
    public static final String REGEX_HOUR = "^(\\*|(1?[0-9]|2[0-3])(-(1?[0-9]|2[0-3]))?)(\\/[1-9][0-9]*)?(,(\\*|(1?[0-9]|2[0-3])(-(1?[0-9]|2[0-3]))?)(\\/[1-9][0-9]*)?)*$";
    public static final String REGEX_DAY_OF_MONTH = "^(\\*|([1-9]|[1-2][0-9]?|3[0-1])(-([1-9]|[1-2][0-9]?|3[0-1]))?)(\\/[1-9][0-9]*)?(,(\\*|([1-9]|[1-2][0-9]?|3[0-1])(-([1-9]|[1-2][0-9]?|3[0-1]))?)(\\/[1-9][0-9]*)?)*$";
    public static final String REGEX_MONTH = "^(\\*|([1-9]|1[0-2]?)(-([1-9]|1[0-2]?))?)(\\/[1-9][0-9]*)?(,(\\*|([1-9]|1[0-2]?)(-([1-9]|1[0-2]?))?)(\\/[1-9][0-9]*)?)*$";
    public static final String REGEX_DAY_OF_WEEK = "^(\\*|[0-6](-[0-6])?)(\\/[1-9][0-9]*)?(,(\\*|[0-6](-[0-6])?)(\\/[1-9][0-9]*)?)*$";
}
