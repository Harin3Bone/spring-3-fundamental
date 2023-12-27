package com.tutorial.springfundamental.utils;

import com.tutorial.springfundamental.exception.InvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDate stringToLocalDate(String date) {
        try {
            var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new InvalidException("Invalid date format. Please use yyyy-MM-dd");
        }
    }
}
