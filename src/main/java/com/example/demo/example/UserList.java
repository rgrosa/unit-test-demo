package com.example.demo.example;

import com.example.demo.example.external.EmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class UserList {

    List<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
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
    public boolean sendEmail(String message, String name){
        EmailService emailService = new EmailService();
        User userObject = getUserByName(name);
        boolean isEmailSend = emailService.sendEmail(message, userObject);
        if(isEmailSend){
            System.out.println("Email sent");
         return true;
        }
            System.out.println("Email not sent");
        return false;
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
