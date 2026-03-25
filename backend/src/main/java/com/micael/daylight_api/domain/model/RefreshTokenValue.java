package com.micael.daylight_api.domain.model;

import java.time.Instant;

public record RefreshTokenValue(
        String value,
        Instant expiresAt
) {
}
