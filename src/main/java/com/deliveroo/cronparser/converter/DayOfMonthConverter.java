package com.deliveroo.cronparser.converter;

import org.springframework.stereotype.Service;

import static com.deliveroo.cronparser.utils.RegexUtils.REGEX_DAY_OF_MONTH;

@Service
public class DayOfMonthConverter implements CronConverter{

    @Override
    public boolean isValid(String arg) {
        return arg.matches(REGEX_DAY_OF_MONTH);
    }

    @Override
    public int getMax() {
        return 31;
    }

    @Override
    public String getKey() {
        return "day of month ";
    }

    @Override
    public int getStart() {
        return 1;
    }
}
