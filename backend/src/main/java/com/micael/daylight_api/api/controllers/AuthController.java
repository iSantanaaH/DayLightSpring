package com.micael.daylight_api.api.controllers;

import com.micael.daylight_api.api.dto.AuthRequest;
import com.micael.daylight_api.application.auth.RegisterRequest;
import com.micael.daylight_api.application.auth.RegisterUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RegisterUseCase registerUseCase;

    @PostMapping("/register")
   public String register(@RequestBody RegisterRequest request){
    return registerUseCase.register(request);
   }
}
