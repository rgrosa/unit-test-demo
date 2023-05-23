package com.example.demo.example.external;

import com.example.demo.example.User;

public class EmailService {

    //example of a external service
    public boolean sendEmail(String message, User user){
        return Math.round(Math.random()) == 1;
    }
}