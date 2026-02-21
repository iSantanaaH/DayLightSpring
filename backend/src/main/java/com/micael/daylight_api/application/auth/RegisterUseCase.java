package com.micael.daylight_api.application.auth;

import com.micael.daylight_api.domain.enums.Role;
import com.micael.daylight_api.domain.model.User;
import com.micael.daylight_api.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterUseCase {
	private final UserRepository userRepository;

	public RegisterUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String register(RegisterRequest registerRequest) {

		if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}


		User user = new User(
				registerRequest.getName(),
				registerRequest.getEmail(),
				registerRequest.getPassword(),
				registerRequest.getBirthDate(),
				registerRequest.getGender(),
				Role.USER,
				LocalDate.now(),
				LocalDate.now()
		);

		userRepository.save(user);

		return "User registered successfully";
	}
}
