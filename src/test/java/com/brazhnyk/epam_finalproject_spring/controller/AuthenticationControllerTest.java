package com.brazhnyk.epam_finalproject_spring.controller;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@AutoConfigureMockMvc
@DisplayName("[Integration] Controller")
@Feature("Controller")
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationController authController;

    @Test
    @DisplayName("Open registration form page")
    @Story("Authentication Controller")
    void openRegistrationForm() throws Exception {
        this.mockMvc.perform(get("/registration")).andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
//    void openRegistrationFailPage() {
//    }
//
//    @Test
//    void openRegistrationSuccessPage() {
//    }
//
//    @Test
//    void addNewUser() {
//    }
}