package com.deliveroo.cronparser.converter;

import org.springframework.stereotype.Service;

import static com.deliveroo.cronparser.utils.RegexUtils.REGEX_HOUR;

@Service
public class HourCronConverter implements CronConverter{

    @Override
    public boolean isValid(String arg) {
        return arg.matches(REGEX_HOUR);
    }

    @Override
    public int getMax() {
        return 23;
    }

    @Override
    public String getKey() {
        return "hour         ";
    }
}
