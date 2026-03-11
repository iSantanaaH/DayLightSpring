package com.micael.daylight_api.application.auth;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private LocalDate birthdate;

    public RegisterRequest() {}
}
