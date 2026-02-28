package com.micael.daylight_api.application.auth;

public record LoginResponse (
        String accessToken,
        String refreshToken,
        long expiresIn,
        UserResponse user
) {}
