package com.deliveroo.cronparser.service;

import com.deliveroo.cronparser.converter.CronConverter;
import com.deliveroo.cronparser.exception.ExpressionException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CronService {

    private final CronConverter minuteCronConverter;
    private final CronConverter hourCronConverter;
    private final CronConverter dayOfMonthConverter;
    private final CronConverter monthConverter;
    private final CronConverter dayOfWeekConverter;

    public void parse(String[] args) {
        if (args.length == 6){
            printout(minuteCronConverter, args[0]);
            printout(hourCronConverter, args[1]);
            printout(dayOfMonthConverter, args[2]);
            printout(monthConverter, args[3]);
            printout(dayOfWeekConverter, args[4]);
            printCommand(args[5]);
        } else {
            throw new ExpressionException("invalid quantity of values");
        }
    }

    void printout(CronConverter converter, String arg) {
        String value = converter.convert(arg);
        converter.print(value);
    }

    void printCommand(String arg) {
        System.out.println("command       " + arg);
    }
}
