package com.micael.daylight_api.application.auth.responses;

import com.micael.daylight_api.domain.model.RefreshTokenValue;

public record RefreshTokenResponse(
        RefreshTokenValue refreshToken
) {
}
