package com.micael.daylight_api.application.auth.requests;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank String refreshToken) {
}
