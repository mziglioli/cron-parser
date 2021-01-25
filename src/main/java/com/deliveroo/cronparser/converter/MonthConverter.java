package com.deliveroo.cronparser.converter;

import org.springframework.stereotype.Service;

import static com.deliveroo.cronparser.utils.RegexUtils.REGEX_MONTH;

@Service
public class MonthConverter implements CronConverter{

  @Override
  public boolean isValid(String arg) {
      return arg.matches(REGEX_MONTH);
    }

  @Override
  public int getMax() {
        return 12;
    }

  @Override
  public String getKey() {
        return "month        ";
    }

  @Override
  public int getStart() {
    return 1;
  }
}
