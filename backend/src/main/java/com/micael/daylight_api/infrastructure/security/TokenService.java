package com.micael.daylight_api.infrastructure.security;


import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshTokenValue;

import java.time.Instant;

public interface TokenService {
    AccessTokenValue generateAccessToken(String userId, String role);

    RefreshTokenValue generateRefreshToken();

    Instant getAccessTokenExpiration();

    Instant getRefreshTokenExpiration();
}
