package com.micael.daylight_api.application.auth.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginResquest(
        @NotBlank String email,
        @NotBlank String password
) {
}
