package com.micael.daylight_api.infrastructure.security;

public interface TokenService {
    String generateAccessToken(String userId, String role);

    String generateRefreshToken(String accessToken);

    long getExpiration();
}
