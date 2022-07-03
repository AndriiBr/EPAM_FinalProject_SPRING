package com.brazhnyk.epam_finalproject_spring.util;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.Role;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] Utils")
@Feature("Utils")
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
    @DisplayName("[Success] Test input data from registration form")
    @Story("Utils - Input validator")
    void validateRegistrationForm_success() {
        assertTrue(InputValidator.validateRegistrationForm(
                "User1",
                "email@gmail.com",
                "Pass1234",
                "Pass1234"));
    }

    @ParameterizedTest
    @DisplayName("[Fail] Test input data from registration form")
    @Story("Utils - Input validator")
    @MethodSource("stringProviderForRegistrationForm")
    void validateRegistrationForm_fail(String username, String email, String password, String passwordConfirm) {
        assertFalse(InputValidator.validateRegistrationForm(username, email, password, passwordConfirm));
    }

    @Test
    @DisplayName("[Success] Test input data from new edition form")
    @Story("Utils - Input validator")
    void validateNewEdition() {
        assertTrue(InputValidator.validateNewEdition(
                "Test title",
                "Тестова сторінка",
                "Test text",
                "Тестовий текст", "300",
                new Genre()));
    }

    @ParameterizedTest
    @DisplayName("[Fail] Test input data from new edition form")
    @Story("Utils - Input validator")
    @MethodSource("stringProviderForNewEditionForm")
    void validateRegistrationForm_fail(String titleEn, String titleUa, String textEn, String textUa, String price, Genre genre) {
        assertFalse(InputValidator.validateNewEdition(titleEn, titleUa, textEn, textUa, price, genre));
    }

    @Test
    @DisplayName("Test money input validation")
    @Story("Utils - Input validator")
    void validateMoney() {
        assertTrue(InputValidator.validateMoney("257"));
        assertFalse(InputValidator.validateMoney("bynet"));
        assertFalse(InputValidator.validateMoney("099"));
        assertFalse(InputValidator.validateMoney(null));
    }

    @Test
    @DisplayName("Test genre validation")
    @Story("Utils - Input validator")
    void validateGenre() {
        assertTrue(InputValidator.validateGenre(new Genre()));
        assertFalse(InputValidator.validateGenre(null));
    }

    @Test
    @DisplayName("[Success]Test number validation")
    @Story("Utils - Input validator")
    void validateNumberValue() {
        assertTrue(InputValidator.validateNumberValue("435"));
        assertFalse(InputValidator.validateNumberValue("word"));
    }

    static Stream<Arguments> stringProviderForRegistrationForm() {
        return Stream.of(
                Arguments.arguments("usr", "email@gmail.com", "Pass1234", "Pass1234"),
                Arguments.arguments("User1", "emgma.com", "Pass1234", "Pass1234"),
                Arguments.arguments("User1", "email@gmail.com", "pass12", "pass12"),
                Arguments.arguments("User1", "email@gmail.com", "Pass1234", "Pass123456")
        );
    }

    static Stream<Arguments> stringProviderForNewEditionForm() {
        return Stream.of(
                Arguments.arguments(null, "Тестова сторінка", "Test text", "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", null, "Test text", "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", null, "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "Test text", null, "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "Test text", "Тестовий текст", null, new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "Test text", "Тестовий текст", "300", null),
                Arguments.arguments("T", "Тестова сторінка", "Test text", "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", "Т", "Test text", "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "T", "Тестовий текст", "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "Test text", "Т", "300", new Genre()),
                Arguments.arguments("Test title", "Тестова сторінка", "Test text", "Тестовий текст", "rtbne", new Genre())
        );
    }
}