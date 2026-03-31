package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.application.auth.requests.RegisterRequest;
import com.micael.daylight_api.application.exceptions.BadRequestException;
import com.micael.daylight_api.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterUseCaseTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterUseCase registerUseCase;

    @Test
    void should_register_user_successfully() {
        var request = new RegisterRequest(
                "Fake User",
                "teste@teste.com",
                "12345678",
                "(99) 9 9999-9999",
                LocalDate.of(2001, 1, 1)
        );

        when(userRepository.findByEmail(request.email()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.password()))
                .thenReturn("hashedPassword");

        registerUseCase.register(request);

        verify(userRepository).save(argThat(user ->
                user.getEmail().equals(request.email()) &&
                        user.getPassword().equals("hashedPassword") &&
                        user.getName().equals(request.name()) &&
                        user.getPhone().equals(request.phone()) &&
                        user.getBirthdate().equals(request.birthdate())
        ));
    }

    @Test
    void should_throw_exception_when_email_already_exists() {
        var request = new RegisterRequest(
                "Fake User",
                "teste@teste.com",
                "12345678",
                "(99) 9 9999-9999",
                LocalDate.of(2001, 1, 1)
        );

        var existingUser = UserTestBuilder.userForRegister(
                "teste@teste.com",
                "12345678"
        );

        when(userRepository.findByEmail(request.email()))
                .thenReturn(Optional.of(existingUser));

        var exception = assertThrows(BadRequestException.class, () ->
                registerUseCase.register(request)
        );

        assertEquals("Email already exists", exception.getMessage());

        verify(userRepository, never()).save(any());
    }
}
