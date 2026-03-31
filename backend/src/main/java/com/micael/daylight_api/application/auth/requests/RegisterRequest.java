package com.micael.daylight_api.application.auth.requests;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String phone,
        @NotBlank LocalDate birthdate
) {
}
