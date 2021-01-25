package com.deliveroo.cronparser.converter;

import org.springframework.stereotype.Service;

import static com.deliveroo.cronparser.utils.RegexUtils.REGEX_DAY_OF_WEEK;

@Service
public class DayOfWeekConverter implements CronConverter{

  @Override
  public boolean isValid(String arg) {
      return arg.matches(REGEX_DAY_OF_WEEK);
    }

  @Override
  public int getMax() {
        return 6;
    }

  @Override
  public String getKey() {
        return "day of week  ";
    }

}
