package com.example.demo.spring;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("DateUtilTest")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class DateUtilTestTest {

    /*There is a school of thought that says that every test
    / function in a JUnit test should hava one and only one assert statemente ...
    / see https://www.artima.com/weblogs/viewpost.js%E1%B9%95?thread=35578
    / also test's should be single concept per function
    */


    //2023-01-01T00:00:00Z
    @AfterAll
     static void setup(){
        System.out.println("End of the tests");
    }

    @Nested
    class nonParameterizedTests{

        @Test
        @DisplayName("testDateInvalidFormatterDDMMYYYYBHHMISS")
        void testDateInvalidFormatterDDMMYYYYBHHMISS() {
            Assert.assertThrows(Exception.class,
                    () ->
                            DateUtil.string2LocalDateTime("01/11/2023 00:00:00","yyyy-MM-dd'T'HH:mm:ss'Z")
            );
        }

        @Test
        @DisplayName("testDateInvalidFormatterMMDDYYYYBHHMISS")
        void testDateInvalidFormatterMMDDYYYYBHHMISS() {
            Assert.assertThrows(Exception.class,
                    () ->
                            DateUtil.string2LocalDateTime("11/01/2023 00:00:00","yyyy-MM-dd'T'HH:mm:ss'Z")
            );
        }

        @Test
        @DisplayName("testDateInvalidFormatterMilliSeconds")
        void testDateInvalidFormatterMilliSeconds() {
            Assert.assertThrows(Exception.class,
                    () ->
                            DateUtil.string2LocalDateTime("2023-01-01T00:00:00.000Z","yyyy-MM-dd'T'HH:mm:ss'Z")
            );
        }
    }

    @Nested
    class parameterizedTests{


        // Separated logic from test data
        @ParameterizedTest
        @ValueSource(strings = {"01/11/2023 00:00:00", "11/01/2023 00:00:00", "2023-01-01T00:00:00.000Z"})
        @DisplayName("testDateInvalidFormatter")
        void testDateInvalidFormatter(String invalidDateFormat) {
            Assert.assertThrows(Exception.class,
                    () ->
                            DateUtil.string2LocalDateTime(invalidDateFormat,"yyyy-MM-dd'T'HH:mm:ss'Z")
            );
        }

        // Separated logic from test data
        @ParameterizedTest
        @CsvSource( {
                "01/11/2023 00:00:00, yyyy-MM-dd'T'HH:mm:ss'Z",
                "11/01/2023 00:00:00, dd-MM-yyyy'T'HH:mm:ss",
                "2023-01-01T00:00:00.000Z, yyyy-MM-dd'T'HH:mm:ss'Z"}
        )
        @DisplayName("testDateInvalidFormatterOtherPatterns")
        void testDateInvalidFormatterOtherPatterns(String invalidDateFormat, String pattern) {
            Assert.assertThrows(Exception.class,
                    () ->
                            DateUtil.string2LocalDateTime(invalidDateFormat, pattern)
            );
        }
    }

    @Nested
    class otherTests{
        @Test
        @DisplayName("testMethodSpeed")
        void testMethodSpeed() {

            assertTimeout(Duration.ofMillis(10l),
                    () -> DateUtil.string2LocalDateTime("2023-01-01T00:00:00Z","yyyy-MM-dd'T'HH:mm:ss'Z'")
                            .isBefore(LocalDateTime.MAX)
            );
        }
    }
}