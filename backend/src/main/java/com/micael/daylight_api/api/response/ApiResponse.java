package com.micael.daylight_api.api.response;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        int status,
        String message,
        LocalDateTime timestamp,
        T data
) {
}
