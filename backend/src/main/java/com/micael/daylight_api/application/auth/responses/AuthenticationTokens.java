package com.micael.daylight_api.application.auth.responses;

import java.time.LocalDateTime;

public record AuthenticationTokens(
        String value,
        LocalDateTime expiresAt
) {
}
