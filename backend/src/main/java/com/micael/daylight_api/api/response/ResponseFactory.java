package com.micael.daylight_api.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseFactory {
    public static <T> ResponseEntity<ApiResponse<T>> success(
            HttpStatus status,
            String message,
            T data
    ) {
        return buildResponse(status, message, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(
            HttpStatus status,
            String message
    ) {
        return buildResponse(status, message, null);
    }

    private static <T> ResponseEntity<ApiResponse<T>> buildResponse(
            HttpStatus status,
            String message,
            T data
    ) {
        ApiResponse<T> response = new ApiResponse<>(
                status.value(),
                message,
                LocalDateTime.now(),
                data
        );

        return ResponseEntity.status(status).body(response);
    }
}
