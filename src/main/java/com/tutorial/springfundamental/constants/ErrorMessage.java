package com.tutorial.springfundamental.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

    public static final String NOT_FOUND = "%s not found";
    public static final String INVALID_SORT_BY = "unable to sort by column %s";

}
