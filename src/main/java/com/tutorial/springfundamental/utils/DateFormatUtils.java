package com.tutorial.springfundamental.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatUtils {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static LocalDate stringToLocalDate(String date) {
        var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter);
    }
}
