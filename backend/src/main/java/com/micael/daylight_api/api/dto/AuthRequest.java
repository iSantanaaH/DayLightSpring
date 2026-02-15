package com.micael.daylight_api.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String Email;
    private String Password;
}
