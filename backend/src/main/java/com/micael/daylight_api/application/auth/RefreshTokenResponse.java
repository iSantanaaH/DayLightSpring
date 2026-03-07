package com.micael.daylight_api.application.auth;

public record RefreshTokenResponse (String accessToken, String refreshToken) {
}
