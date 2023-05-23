package com.example.demo.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
public class DateUtil {

    public  LocalDateTime string2LocalDateTime(String value, String pattern) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("UTC"));
            return LocalDateTime.parse(value, formatter);
        }catch(Exception ex){
            throw new Exception("Error: string could not be parsed to LocalDateTime");
        }
    }
}