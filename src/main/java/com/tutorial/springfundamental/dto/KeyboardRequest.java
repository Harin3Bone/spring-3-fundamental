package com.tutorial.springfundamental.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record KeyboardRequest(

        @NotNull
        String name,

        @Min(0)
        int quantity,

        @Min(0)
        @Digits(integer = 9, fraction = 4)
        double price
) {

}
