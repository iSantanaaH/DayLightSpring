package com.micael.daylight_api.api.controllers;

import com.micael.daylight_api.api.dto.AuthRequest;
import com.micael.daylight_api.application.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
   public ResponseEntity<LoginResponse> login(@RequestBody LoginResquest request){
    return ResponseEntity.ok(loginUseCase.login(request));
   }
}
