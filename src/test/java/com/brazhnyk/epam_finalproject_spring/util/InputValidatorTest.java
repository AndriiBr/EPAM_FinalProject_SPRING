package com.brazhnyk.epam_finalproject_spring.util;

import com.brazhnyk.epam_finalproject_spring.entity.Role;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @BeforeAll
    static void setUp() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("Pass1234");
        user.setEmail("admin@test.com");
        user.setId(1L);
        user.setBalance(1000);
        user.setRoles(new HashSet<>(Collections.singleton(Role.ROLE_ADMIN)));
    }

    @Test
    void validateLoginPassword() {
    }

    @Test
    void validateRegistrationForm() {
    }

    @Test
    void validateNewEdition() {
    }

    @Test
    void validateMoney() {
    }

    @Test
    void validateGenre() {
    }

    @Test
    void validateNumberValue() {
    }
}