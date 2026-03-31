package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.application.auth.requests.RegisterRequest;
import com.micael.daylight_api.application.exceptions.ConflictException;
import com.micael.daylight_api.domain.model.User;
import com.micael.daylight_api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new ConflictException("Email already exists");
        }

        if (userRepository.existsByPhone(registerRequest.phone())) {
            throw new ConflictException("Phone already exists");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.password());

        User user = User.create(
                registerRequest.name(),
                registerRequest.email(),
                encodedPassword,
                registerRequest.phone(),
                registerRequest.birthdate()
        );

        userRepository.save(user);
    }
}
