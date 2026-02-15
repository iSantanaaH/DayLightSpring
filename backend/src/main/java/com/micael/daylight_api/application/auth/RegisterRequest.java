package com.micael.daylight_api.application.auth;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String gender;

    public RegisterRequest() {}
}
