package com.example.demo;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime string2LocalDateTime(String value) throws Exception {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));
            return LocalDateTime.parse(value, formatter);
        }catch(Exception ex){
            throw new Exception("Date format pattern is not recognized, use this pattern `yyyy-MM-dd'T'HH:mm:ss'Z'`");
        }
    }
}