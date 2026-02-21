package com.micael.daylight_api.application.auth;

import lombok.Getter;

@Getter
public class LoginResquest {
    private String email;
    private String password;

    public LoginResquest() {}
}
