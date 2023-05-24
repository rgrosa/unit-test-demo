package com.example.demo.example.external;

import com.example.demo.example.User;

public class EmailService {

    //example of a external service
    public boolean sendEmail(String message, String user) throws RuntimeException {
        if(message.length() > 1000){
            throw new RuntimeException("Error at sending email");
        }
        return Math.round(Math.random()) == 1;
    }
}