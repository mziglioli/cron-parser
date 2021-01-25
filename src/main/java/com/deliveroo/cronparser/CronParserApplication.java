package com.deliveroo.cronparser;

import com.deliveroo.cronparser.converter.*;
import com.deliveroo.cronparser.service.CronService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CronParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CronParserApplication.class, args);

        CronService cronService = new CronService(new MinuteCronConverter(), new HourCronConverter(), new DayOfMonthConverter(), new MonthConverter(), new DayOfWeekConverter());
        cronService.parse(args);
    }

}
