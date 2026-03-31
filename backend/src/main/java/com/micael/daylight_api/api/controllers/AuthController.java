package com.micael.daylight_api.api.controllers;

import com.micael.daylight_api.api.response.ApiResponse;
import com.micael.daylight_api.application.auth.requests.LoginResquest;
import com.micael.daylight_api.application.auth.requests.RefreshTokenRequest;
import com.micael.daylight_api.application.auth.requests.RegisterRequest;
import com.micael.daylight_api.application.auth.responses.LoginResponse;
import com.micael.daylight_api.application.auth.responses.RefreshTokenResponse;
import com.micael.daylight_api.application.auth.usecases.LoginUseCase;
import com.micael.daylight_api.application.auth.usecases.RefreshTokenUseCase;
import com.micael.daylight_api.application.auth.usecases.RegisterUseCase;
import jakarta.validation.Valid;
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
    private final RefreshTokenUseCase refreshTokenUseCase;

    @Autowired
    public AuthController(
            LoginUseCase loginUseCase,
            RegisterUseCase registerUseCase,
            RefreshTokenUseCase refreshTokenUseCase
    ) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>>
    register(@Valid @RequestBody RegisterRequest request) {
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
    public ResponseEntity<ApiResponse<LoginResponse>>
    login(@Valid @RequestBody LoginResquest request) {
        LoginResponse authenticateUser = loginUseCase.login(request);

        ApiResponse<LoginResponse> response = new ApiResponse<>(
                200,
                "login successful",
                LocalDateTime.now(),
                authenticateUser
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>>
    refreshToken(@Valid @RequestBody RefreshTokenRequest request) {

        RefreshTokenResponse refreshToken = refreshTokenUseCase.refreshToken(request);

        ApiResponse<RefreshTokenResponse> response = new ApiResponse<>(
                200,
                "re authenticated with success",
                LocalDateTime.now(),
                refreshToken
        );

        return ResponseEntity.ok(response);
    }
}
