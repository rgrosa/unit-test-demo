package com.example.demo.example;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("DateUtilTest")
public class DateUtilTest {
    /*There is a school of thought that says that every test
    / function in a JUnit test should hava one and only one assert statemente ...
    / see https://www.artima.com/weblogs/viewpost.js%E1%B9%95?thread=35578
    / also test's should be single concept per function
    */


    DateUtil dateUtil;

    @BeforeAll
    public static void setupBeforeAll(){
        System.out.println("Starting tests at: "+ new Date());
    }

    @BeforeEach
    public void setupBefore(){
        this.dateUtil = new DateUtil();
    }

    @AfterAll
    public static void setupAfter(){
        System.out.println("End tests at: " + new Date());
    }

    @Test
    @DisplayName("shouldReturnLocalDateTimeNotNull")
    public void shouldReturnLocalDateTimeNotNull() throws Exception {
        //GIVEN
        String value = "2023-01-01T00:00:00Z";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        //WHEN
        LocalDateTime date = this.dateUtil.string2LocalDateTime(
                value,
                pattern
        );

        //THEN
        Assert.assertTrue(date != null);
    }


    @Test
    @DisplayName("shouldReturnLocalDateTime")
    public void shouldReturnLocalDateTime() throws Exception {
        //GIVEN
        String value = "2023-01-01T00:00:00Z";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        //WHEN
        LocalDateTime date = this.dateUtil.string2LocalDateTime(
                value,
                pattern
        );

        //THEN
        Assert.assertFalse(date == null);
    }

    @Test
    @DisplayName("testLimitsOfObject")
    public void testLimitsOfObject() throws Exception {
        //GIVEN
        String value = "2023-01-01T00:00:00Z";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        //WHEN
        LocalDateTime date = this.dateUtil.string2LocalDateTime(
                value,
                pattern
        );

        //THEN
        Assertions.assertAll(
                () -> Assert.assertTrue(date.isBefore(LocalDateTime.MAX)),
                () -> Assert.assertTrue(date.isAfter(LocalDateTime.MIN))
        );
    }

    @Test
    @DisplayName("shouldThrowException")
    public void shouldThrowException() throws Exception {
        String value = "2023-01-01T00:00:00Z";
        String pattern = "dd/MM/YYYY HH:mm:ss";

        Assert.assertThrows(Exception.class,
                () ->  this.dateUtil.string2LocalDateTime(
                        value,
                        pattern
                )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"01/11/2023 00:00:00", "11/01/2023 00:00:00", "2023-01-01T00:00:00.000Z"})
    @DisplayName("testDateInvalidFormatterParameterized")
    void testDateInvalidFormatter(String invalidDateFormat) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z";

        Assert.assertThrows(Exception.class,
                () ->
                        this.dateUtil.string2LocalDateTime(invalidDateFormat, pattern)
        );
    }


    @Nested
    class speedTest{

        DateUtil dateUtil;

        @BeforeEach
        public void setupBefore(){
            this.dateUtil = new DateUtil();
            System.out.println("I ONLY WILL APPEAR IN SPEEDTEST SCOPE");
        }

        @Test
        @DisplayName("testMethodSpeed")
        void testMethodSpeed() {
            String value = "2023-01-01T00:00:00Z";
            String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

            assertTimeout(Duration.ofMillis(10l),
                    () -> this.dateUtil.string2LocalDateTime(value, pattern)
                            .isBefore(LocalDateTime.MAX)
            );
        }
    }

    @RepeatedTest(10)
    @DisplayName("testMethodSpeed")
    void repeatTestXTimes() {
        String value = "2023-01-01T00:00:00Z";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        assertTimeout(Duration.ofMillis(10l),
                () -> this.dateUtil.string2LocalDateTime(value, pattern)
                        .isBefore(LocalDateTime.MAX)
        );
    }

    @Disabled
    @RepeatedTest(10000)
    @DisplayName("testMethodSpeed")
    void repeatTestMillionTimes() {
        String value = "2023-01-01T00:00:00Z";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

        assertTimeout(Duration.ofMillis(10l),
                () -> this.dateUtil.string2LocalDateTime(value, pattern)
                        .isBefore(LocalDateTime.MAX)
        );
    }
}
