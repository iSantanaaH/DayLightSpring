package com.micael.daylight_api.domain.repository;

import com.micael.daylight_api.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}
