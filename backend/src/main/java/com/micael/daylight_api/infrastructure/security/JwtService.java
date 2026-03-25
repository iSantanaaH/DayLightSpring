package com.micael.daylight_api.infrastructure.security;

import com.micael.daylight_api.domain.model.AccessTokenValue;
import com.micael.daylight_api.domain.model.RefreshTokenValue;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
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
        LocalDateTime expireAt = LocalDateTime.now()
                .plusSeconds(accessTokenExpiration / 1000);

        String token = Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(key)
                .compact();

        return new AccessTokenValue(token, expireAt);
    }

    public RefreshTokenValue generateRefreshToken() {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now()
                .plusSeconds(refreshTokenExpiration / 1000);

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
