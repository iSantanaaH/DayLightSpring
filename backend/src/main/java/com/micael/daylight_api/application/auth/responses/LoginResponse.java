package com.micael.daylight_api.application.auth.responses;

import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshTokenValue;

public record LoginResponse(
        AccessTokenValue accessTokenValue,
        RefreshTokenValue refreshTokenValue,
        UserResponse user
) {
}
