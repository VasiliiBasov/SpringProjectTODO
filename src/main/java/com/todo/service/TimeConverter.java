package com.todo.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Data
@Service
public class TimeConverter {

    public String displayTime(BigInteger ms) {
        Long totalSecs = ms.longValue()/1000;
        Long hours = totalSecs / 3600;
        Long minutes = (totalSecs % 3600) / 60;
        Long seconds = totalSecs % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
