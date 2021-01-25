package com.deliveroo.cronparser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CronDto {
    private int init;
    private int end;
    private int inc;
}
