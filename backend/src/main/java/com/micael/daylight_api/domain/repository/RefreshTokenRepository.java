package com.micael.daylight_api.domain.repository;

import com.micael.daylight_api.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByToken(String token);

    void save(RefreshToken token);
}
