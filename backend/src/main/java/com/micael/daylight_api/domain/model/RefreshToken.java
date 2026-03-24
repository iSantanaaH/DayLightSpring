package com.micael.daylight_api.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expires_at;

    @Column(nullable = false)
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime created_at;

    public RefreshToken() {
    }

    public RefreshToken(String token, LocalDateTime expires_at, boolean revoked, User user, LocalDateTime created_at) {
        this.token = token;
        this.expires_at = expires_at;
        this.revoked = false;
        this.user = user;
        this.created_at = created_at;
    }

    public void setRevoked() {
        this.revoked = true;
    }
}
