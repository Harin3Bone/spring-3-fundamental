package com.tutorial.springfundamental.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

public record KeyboardRequest(

        @NotNull
        String name,

        @UUID
        @NotNull
        String categoryId,

        @Min(0)
        int quantity,

        @Min(0)
        @Digits(integer = 9, fraction = 4)
        double price
) {

}
