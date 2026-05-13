package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.application.auth.requests.RefreshTokenRequest;
import com.micael.daylight_api.application.auth.responses.RefreshTokenResponse;
import com.micael.daylight_api.application.exceptions.UnauthorizedException;
import com.micael.daylight_api.domain.repository.RefreshTokenRepository;
import com.micael.daylight_api.infrastructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public RefreshTokenUseCase(
            RefreshTokenRepository refreshTokenRepository,
            TokenService tokenService,
            PasswordEncoder passwordEncoder) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        var token = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));

        if (token.isRevoked()) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        if (token.getExpiresAt().isBefore(Instant.now())) {
            throw new UnauthorizedException("Refresh token is expired");
        }

        token.revoked();
        refreshTokenRepository.save(token);

        var newRefreshToken = tokenService.generateRefreshToken();

        return new RefreshTokenResponse(newRefreshToken);
    }
}
