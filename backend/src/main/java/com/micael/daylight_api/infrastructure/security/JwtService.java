package com.micael.daylight_api.infrastructure.security;

import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshTokenValue;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService implements TokenService {
    private final SecretKey key;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.accessTokenExpiration}") long accessTokenExpiration,
            @Value("${security.jwt.refreshTokenExpiration}") long refreshTokenExpiration
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    @Override
    public AccessTokenValue generateAccessToken(String userId, String role) {
        Instant expireAt = Instant.now()
                .plusMillis(accessTokenExpiration);

        Instant now = Instant.now();

        String token = Jwts.builder()
                .subject(userId)
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(refreshTokenExpiration)))
                .signWith(key)
                .compact();

        return new AccessTokenValue(token, expireAt);
    }

    public RefreshTokenValue generateRefreshToken() {
        String token = UUID.randomUUID().toString();

        Instant expiresAt = Instant.now()
                .plusMillis(refreshTokenExpiration);

        return new RefreshTokenValue(
                token,
                expiresAt
        );
    }

    public long getAccessTokenExpiration() {
        return accessTokenExpiration / 1000;
    }

    public long getRefreshTokenExpiration() {
        return refreshTokenExpiration / 1000;
    }
}
