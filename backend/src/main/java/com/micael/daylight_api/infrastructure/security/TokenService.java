package com.micael.daylight_api.infrastructure.security;


import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshTokenValue;

public interface TokenService {
    AccessTokenValue generateAccessToken(String userId, String role);

    RefreshTokenValue generateRefreshToken();

    long getAccessTokenExpiration();

    long getRefreshTokenExpiration();
}
