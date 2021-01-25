package com.deliveroo.cronparser.converter;

import org.springframework.stereotype.Service;

import static com.deliveroo.cronparser.utils.RegexUtils.REGEX_MINUTE;

@Service
public class MinuteCronConverter implements CronConverter{

  @Override
    public boolean isValid(String arg) {
      return arg.matches(REGEX_MINUTE);
    }

    @Override
    public int getMax() {
        return 59;
    }

    @Override
    public String getKey() {
        return "minute       ";
    }
}
