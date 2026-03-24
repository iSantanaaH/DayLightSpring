package com.micael.daylight_api.domain.model;

import java.time.LocalDateTime;

public record AccessTokenValue(
        String value,
        LocalDateTime expiresAt
) {
}
