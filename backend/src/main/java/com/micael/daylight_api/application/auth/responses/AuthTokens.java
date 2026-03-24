package com.micael.daylight_api.application.auth.responses;

public record AuthTokens(
        AuthenticationTokens accessToken,
        AuthenticationTokens refreshToken
) {
}
