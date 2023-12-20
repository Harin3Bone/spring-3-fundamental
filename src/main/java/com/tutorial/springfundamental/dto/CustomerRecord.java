package com.tutorial.springfundamental.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRecord(

        @NotNull
        String username,

        @NotNull
        String password,

        @Email
        String email,

        @NotNull
        String dateOfBirth
) {
}
