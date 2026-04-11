package com.micael.daylight_api.application.auth.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Phone is required")
        String phone,

        @NotNull(message = "Birthdate is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthdate
) {
}
