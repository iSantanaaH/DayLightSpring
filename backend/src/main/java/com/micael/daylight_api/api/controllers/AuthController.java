package com.micael.daylight_api.api.controllers;

import com.micael.daylight_api.api.response.ApiResponse;
import com.micael.daylight_api.application.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;

    @Autowired
    public AuthController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody RegisterRequest request) {
        registerUseCase.register(request);

        ApiResponse<Void> response = new ApiResponse<>(
                201,
                "user registered successfully",
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
   public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginResquest request){
        LoginResponse authenticateUser = loginUseCase.login(request);

        ApiResponse<LoginResponse> response = new ApiResponse<>(
                200,
                "login successful",
                LocalDateTime.now(),
                authenticateUser
        );

        return ResponseEntity.ok(response);
   }
}
