package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.domain.enums.Role;
import com.micael.daylight_api.domain.model.User;
import com.micael.daylight_api.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterUseCase {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public RegisterUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public String register(RegisterRequest registerRequest) {

		if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

		User user = new User(
				registerRequest.getName(),
				registerRequest.getEmail(),
				encodedPassword,
				registerRequest.getBirthDate(),
				registerRequest.getGender(),
				Role.USER
		);

		userRepository.save(user);

		return "User registered successfully";
	}
}
