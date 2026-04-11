package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.application.auth.requests.LoginRequest;
import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshToken;
import com.micael.daylight_api.domain.model.RefreshTokenValue;
import com.micael.daylight_api.domain.repository.RefreshTokenRepository;
import com.micael.daylight_api.domain.repository.UserRepository;
import com.micael.daylight_api.infrastructure.security.TokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private LoginUseCase loginUseCase;

    @Test
    void should_login_successfully() {
        var request = new LoginRequest("teste@teste.com", "12345678");

        var user = UserTestBuilder.userForLogin(
                "teste@teste.com",
                "12345678"
        );

        when(userRepository.findByEmail(request.email())).thenReturn(Optional.of(user));

        when(passwordEncoder.matches(request.password(), user.getPassword())).thenReturn(true);

        var accessToken = new AccessTokenValue(
                "access-token",
                Instant.now().plusSeconds(300)
        );

        var refreshToken = new RefreshTokenValue(
                "refresh-token",
                Instant.now().plusSeconds(600)
        );

        when(tokenService.generateAccessToken(any(), any()))
                .thenReturn(accessToken);

        when(tokenService.generateRefreshToken())
                .thenReturn(refreshToken);

        when(passwordEncoder.encode(refreshToken.value()))
                .thenReturn("hashed-refresh-token");

        var response = loginUseCase.login(request);

        assertNotNull(response);
        assertEquals("access-token", response.accessTokenValue().value());
        assertEquals("refresh-token", response.refreshTokenValue().value());

        ArgumentCaptor<RefreshToken> captor = ArgumentCaptor.forClass(RefreshToken.class);

        verify(refreshTokenRepository).save(captor.capture());

        var savedRefreshToken = captor.getValue();

        assertNotNull(savedRefreshToken);

        assertEquals("hashed-refresh-token", savedRefreshToken.getToken());
        assertEquals(user.getEmail(), savedRefreshToken.getUser().getEmail());
        assertEquals(refreshToken.expiresAt(), savedRefreshToken.getExpiresAt());

        verify(userRepository).findByEmail(request.email());
    }
}
