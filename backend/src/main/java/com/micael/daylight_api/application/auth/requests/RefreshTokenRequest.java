package com.micael.daylight_api.application.auth.requests;

import com.micael.daylight_api.domain.model.RefreshTokenValue;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank RefreshTokenValue refreshToken) {
}
