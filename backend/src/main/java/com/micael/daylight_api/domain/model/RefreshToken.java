package com.micael.daylight_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(nullable = false)
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected RefreshToken() {
    }

    private RefreshToken(String token, Instant expiresAt, boolean revoked, User user, Instant createdAt) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
        this.user = user;
        this.createdAt = createdAt;
    }

    public static RefreshToken create(String token, Instant expiresAt, User user) {
        return new RefreshToken(
                token,
                expiresAt,
                false,
                user,
                Instant.now());
    }

    public void revoked() {
        this.revoked = true;
    }
}
