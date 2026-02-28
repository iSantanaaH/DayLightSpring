package com.micael.daylight_api.application.auth;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        String status,
        java.time.LocalDate createdAt,
        String updatedAt
) {
}
