package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.application.auth.requests.LoginResquest;
import com.micael.daylight_api.application.auth.responses.LoginResponse;
import com.micael.daylight_api.application.auth.responses.UserResponse;
import com.micael.daylight_api.application.exceptions.BadRequestException;
import com.micael.daylight_api.application.exceptions.NotFoundException;
import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshToken;
import com.micael.daylight_api.domain.model.RefreshTokenValue;
import com.micael.daylight_api.domain.repository.RefreshTokenRepository;
import com.micael.daylight_api.domain.repository.UserRepository;
import com.micael.daylight_api.infrastructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginUseCase(
            UserRepository userRepository,
            TokenService tokenService,
            PasswordEncoder passwordEncoder,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public LoginResponse login(LoginResquest loginResquest) {
        var user = userRepository.findByEmail(loginResquest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(loginResquest.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        AccessTokenValue accessToken = tokenService.generateAccessToken(
                user.getId().toString(),
                user.getEmail()
        );

        RefreshTokenValue refreshToken = tokenService.generateRefreshToken();
        String refreshTokenHashed = passwordEncoder.encode(refreshToken.value());

        RefreshToken entity = RefreshToken.create(
                refreshTokenHashed,
                refreshToken.expiresAt(),
                user
        );

        refreshTokenRepository.save(entity);

        var userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getStatus()
        );

        return new LoginResponse(
                accessToken,
                refreshToken,
                userResponse
        );
    }
}
