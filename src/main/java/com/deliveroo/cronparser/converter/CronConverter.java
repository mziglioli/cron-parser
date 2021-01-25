package com.deliveroo.cronparser.converter;

import com.deliveroo.cronparser.dto.CronDto;
import com.deliveroo.cronparser.exception.ExpressionException;

import static com.deliveroo.cronparser.utils.MessageUtils.*;

public interface CronConverter {

    boolean isValid(String arg);
    default String convert(String arg) {
        if (!isValid(arg)) {
            throw new ExpressionException("invalid argument at: " + getKey());
        }
        if (hasSpecificValues(arg)) {
            return replaceSpecificValues(arg);
        }
        CronDto dto = convertToDto(arg);
        return convert(dto);
    }
    default String convert(CronDto dto) {
        StringBuilder sb = new StringBuilder();
        for (int i = dto.getInit(); i <= dto.getEnd(); i += dto.getInc()) {
            sb.append(" ");
            sb.append(i);
        }
        return sb.toString();
    }

    default CronDto convertToDto(String arg) {
        int start = getStart();
        int max = getMax();
        int inc = 1;
        if (hasRange(arg)) {
            String[] elements = arg.split(RANGE);
            start = Integer.parseInt(elements[0]);
            max = Integer.parseInt(elements[1]);
        }
        if (hasIncrement(arg)) {
            String[] elements = arg.split(INCREMENT);
            if (!isAll(elements[0])) {
                start = Integer.parseInt(elements[0]);
            }
            inc = Integer.parseInt(elements[1]);
        }
        return new CronDto(start, max, inc);
    }
    default int getStart() {
        return 0;
    }
    int getMax();
    String getKey();

    default void print(String message) {
        System.out.println(getKey() + message);
    }
}
