package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
