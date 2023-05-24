package com.example.demo.example.mockito;


import com.example.demo.example.UserList;
import com.example.demo.example.external.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserListTestMockito {


    //INJECT MOCK CLASS INTO THIS CLASS
    @InjectMocks
    UserList userList;

    //MOCK CLASS
    @Mock
    EmailService emailService;

    @Test
    void sendEmailShouldBeTrue() throws Exception {
        //given
        when(emailService.sendEmail("email@gmail.com", "renan")).thenReturn(true);

        //when
        String  returnValue = userList.sendEmail("email@gmail.com","renan");

        //then
        Assertions.assertTrue(returnValue.equalsIgnoreCase("Email sent"));
    }

    @Test
    void sendEmailShouldBeFalse() throws Exception {
        //given
        when(emailService.sendEmail("email@gmail.com", "renan")).thenReturn(false);

        //when
        String returnValue = userList.sendEmail("email@gmail.com","renan");

        //then
        Assertions.assertTrue(returnValue.equalsIgnoreCase("Email not sent"));
    }

    @Test
    void sendEmailShouldThrowException() throws Exception {
        //given
        when(emailService.sendEmail(any(), any())).thenThrow(RuntimeException.class);

        //when
        String returnValue = userList.sendEmail("email@gmail.com","renan");

        //then
        Assertions.assertTrue(returnValue.equalsIgnoreCase("error sending email"));
    }

    @Test
    void sendEmailShouldOnlyBeCallOnce() throws Exception {
        //given
        when(emailService.sendEmail(any(), any())).thenReturn(true);

        //when
        userList.sendEmail(null,null);

        //then
        verify(emailService, times(1)).sendEmail(any(),any());
    }
}
