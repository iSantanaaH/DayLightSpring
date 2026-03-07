package com.micael.daylight_api.application.auth;

public interface TokenService {
    String generateAccessToken(String userId, String role);

    String generateRefreshToken(String accessToken);

    long getExpiration();
}
