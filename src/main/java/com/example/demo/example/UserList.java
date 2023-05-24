package com.example.demo.example;

import com.example.demo.example.external.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class UserList {

    List<User> userList;
    EmailService emailService;

    public UserList() {
        this.userList = new ArrayList<>();
        this.emailService = new EmailService();
    }

    //CREATE USER
    public User createUser(String name, String birthday, String email) throws Exception {
        User user = new User();
        DateUtil dateUtil = new DateUtil();

        user.setName(name);
        user.setBirthday(dateUtil.string2LocalDateTime(birthday,"yyyy-MM-dd'T'HH:mm:ss'Z'"));
        user.setEmail(validateEmailInput(email));
        user.setId(userList == null ? 1 :userList.size()+1);
        userList.add(user);
        return user;
    }

    //Validate email
    private String validateEmailInput(String email) throws Exception {
        if(Pattern.compile("^(.+)@(\\S+)$")
                .matcher(email)
                .matches()){
            return email;
        }else {
            throw new Exception("email not valid");
        }
    }

    //Send email
    public String sendEmail(String message, String name) {
        try{
        boolean isEmailSend = emailService.sendEmail(message, name);
        if(isEmailSend){
            return "Email sent";
        }
            return "Email not sent";
        }catch (RuntimeException ex){
            return "error sending email";
        }
    }

    //Get user name
    public User getUserByName(String userName) throws NoSuchElementException {
        return userList.stream().filter(e -> e.getName().equalsIgnoreCase(userName)).findAny().get();
    }

    //Get all
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "userList=" + userList +
                '}';
    }
}
