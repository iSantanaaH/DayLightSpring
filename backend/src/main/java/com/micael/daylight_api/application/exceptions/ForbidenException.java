package com.micael.daylight_api.application.exceptions;

public class ForbidenException extends RuntimeException {
    public ForbidenException(String message) {
        super(message);
    }
}
