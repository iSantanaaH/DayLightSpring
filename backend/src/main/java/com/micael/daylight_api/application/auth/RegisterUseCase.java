package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.application.exceptions.EmailAlreadyExistsException;
import com.micael.daylight_api.domain.enums.UserRole;
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

	public String register(RegisterRequest registerRequest) {

		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new EmailAlreadyExistsException();
		}

		String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

		User user = new User(
				registerRequest.getName(),
				registerRequest.getEmail(),
				encodedPassword,
				registerRequest.getPhone(),
				registerRequest.getBirthdate(),
				UserRole.USER
		);

		userRepository.save(user);

		return "User registered successfully";
	}
}
