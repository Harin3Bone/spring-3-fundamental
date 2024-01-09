package com.tutorial.springfundamental.dto;

import jakarta.validation.constraints.NotNull;

public record KeyboardSearch(

        @NotNull
        String name,

        @NotNull
        int quantity,

        @NotNull
        double priceMin,

        @NotNull
        double priceMax
) {
}
