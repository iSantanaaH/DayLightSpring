package com.micael.daylight_api.application.auth.responses;

import com.micael.daylight_api.domain.enums.UserStatus;

public record UserResponse(
        Long id,
        String name,
        String email,
        UserStatus status
) {
}
