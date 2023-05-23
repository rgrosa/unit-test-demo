package com.example.demo.spring;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
@Component
public class DateUtil {

    public static LocalDateTime string2LocalDateTime(String value, String pattern) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("UTC"));
            return LocalDateTime.parse(value, formatter);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}