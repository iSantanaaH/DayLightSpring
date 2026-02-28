package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.domain.repository.UserRepository;
import com.micael.daylight_api.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public LoginUseCase(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginResquest loginResquest) {

        var user = userRepository.findByEmail(loginResquest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginResquest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateToken(
                user.getId().toString(),
                user.getEmail()
        );
        
        long expiresIn = 3600;
        
        var userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(), 
                user.getRole().name(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null
        );
        
        return new LoginResponse(
          accessToken, 
                "",
          expiresIn,
          userResponse      
        );
    }
}
