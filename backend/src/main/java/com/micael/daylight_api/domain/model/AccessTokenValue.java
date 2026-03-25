package com.micael.daylight_api.domain.model;

import java.time.Instant;

public record AccessTokenValue(
        String value,
        Instant expiresAt
) {
}
