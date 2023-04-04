package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@DisplayName("DateUtilTest")
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class DateUtilTest {

    /*There is a school of thought that says that every test
    / function in a JUnit test should hava one and only one assert statemente ...
    / see https://www.artima.com/weblogs/viewpost.js%E1%B9%95?thread=35578
    / also test's should be single concept per function
    */


    //2023-01-01T00:00:00Z

    @Test
    @DisplayName("testDateInvalidFormatterDDMMYYYYBHHMISS")
    void testDateInvalidFormatterDDMMYYYYBHHMISS() {
        Assert.assertThrows(Exception.class,
                () ->
                        DateUtil.string2LocalDateTime("01/11/2023 00:00:00")
        );
    }

    @Test
    @DisplayName("testDateInvalidFormatterMMDDYYYYBHHMISS")
    void testDateInvalidFormatterMMDDYYYYBHHMISS() {
        Assert.assertThrows(Exception.class,
                () ->
                        DateUtil.string2LocalDateTime("11/01/2023 00:00:00")
        );
    }

    @Test
    @DisplayName("testDateInvalidFormatterMilliSeconds")
    void testDateInvalidFormatterMilliSeconds() {
        Assert.assertThrows(Exception.class,
                () ->
                        DateUtil.string2LocalDateTime("2023-01-01T00:00:00.000Z")
        );
    }

    // Separated logic from test data
    @ParameterizedTest
    @ValueSource(strings = {"01/11/2023 00:00:00", "11/01/2023 00:00:00", "2023-01-01T00:00:00.000Z"})
    void testDateInvalidFormatter(String invalidDateFormat) {
        Assert.assertThrows(Exception.class,
                () ->
                        DateUtil.string2LocalDateTime(invalidDateFormat)
        );
    }

}