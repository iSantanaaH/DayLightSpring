package com.micael.daylight_api.api.controllers;

import com.micael.daylight_api.api.response.ApiResponse;
import com.micael.daylight_api.api.response.ResponseFactory;
import com.micael.daylight_api.application.auth.requests.LoginRequest;
import com.micael.daylight_api.application.auth.requests.RefreshTokenRequest;
import com.micael.daylight_api.application.auth.requests.RegisterRequest;
import com.micael.daylight_api.application.auth.responses.LoginResponse;
import com.micael.daylight_api.application.auth.responses.RefreshTokenResponse;
import com.micael.daylight_api.application.auth.usecases.LoginUseCase;
import com.micael.daylight_api.application.auth.usecases.RefreshTokenUseCase;
import com.micael.daylight_api.application.auth.usecases.RegisterUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

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

        return ResponseFactory.success(
                HttpStatus.CREATED,
                "user registered successfully",
                null
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>>
    login(@Valid @RequestBody LoginRequest request) {
        LoginResponse authenticateUser = loginUseCase.login(request);

        return ResponseFactory.success(
                HttpStatus.OK,
                "user login successfully",
                authenticateUser
        );
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>>
    refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        System.out.println("o RefreshToken é:" + request.refreshToken());
        RefreshTokenResponse refreshToken = refreshTokenUseCase.refreshToken(request);

        return ResponseFactory.success(
                HttpStatus.OK,
                "token refreshed successfully",
                refreshToken
        );
    }
}
