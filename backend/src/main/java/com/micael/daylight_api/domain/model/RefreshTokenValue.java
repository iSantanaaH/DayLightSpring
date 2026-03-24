package com.micael.daylight_api.domain.model;

import java.time.LocalDateTime;

public record RefreshTokenValue(
        String value,
        LocalDateTime expiresAt
) {
}
