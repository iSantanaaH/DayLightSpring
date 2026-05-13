package com.micael.daylight_api.application.auth.responses;

import com.micael.daylight_api.domain.model.AccessTokenValue;

public record LoginResponse(
        AccessTokenValue accessTokenValue,
        String refreshTokenValue,
        UserResponse user
) {
}
