package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.application.exceptions.UnauthorizedException;

public class RefreshTokenRequest {
    private final String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new UnauthorizedException("refreshToken cannot be null or empty");
        }
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
