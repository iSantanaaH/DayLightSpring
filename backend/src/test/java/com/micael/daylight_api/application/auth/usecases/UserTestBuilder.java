package com.micael.daylight_api.application.auth.usecases;

import com.micael.daylight_api.domain.model.User;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

class UserTestBuilder {
    public static User userForLogin(String email, String password) {
        var user = User.create(
                "Fake User",
                email,
                password,
                "(99) 9 9999-9999",
                LocalDate.of(2000, 1, 1)
        );
        ReflectionTestUtils.setField(user, "id", 1L);

        return user;
    }

    public static User userForRegister(String email, String password) {
        var user = User.create(
                "Fake User",
                email,
                password,
                "(99) 9 9999-9999",
                LocalDate.of(2000, 1, 1)
        );
        ReflectionTestUtils.setField(user, "id", 1L);

        return user;
    }
}
