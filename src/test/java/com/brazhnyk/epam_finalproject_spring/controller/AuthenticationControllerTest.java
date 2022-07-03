package com.brazhnyk.epam_finalproject_spring.controller;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "/application-test.properties")
@Sql(value = {"/test_db_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/test_db_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("[Integration] Controller")
@Feature("Controller")
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationController authController;

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("Open login/success page")
    @Story("Authentication Controller")
    void openLoginSuccessPage() throws Exception {
        this.mockMvc
                .perform(get("/login/success"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Open login/success page")
    @Story("Authentication Controller")
    void openLoginFailPage() throws Exception {
        this.mockMvc
                .perform(get("/login/fail"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Open registration form page")
    @Story("Authentication Controller")
    void openRegistrationForm() throws Exception {
        this.mockMvc
                .perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Open registration/fail page")
    @Story("Authentication Controller")
    void openRegistrationFailPage() throws Exception {
        this.mockMvc
                .perform(get("/registration/fail"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Open registration/success page")
    @Story("Authentication Controller")
    void openRegistrationSuccessPage() throws Exception {
        this.mockMvc
                .perform(get("/registration/success"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Add new user in DB")
    @Story("Authentication Controller")
    void addNewUser() throws Exception {
        this.mockMvc
                .perform(post("/registration")
                        .param("username", "testUser3")
                        .param("email", "testUser3@test.com")
                        .param("password", "Test1234")
                        .param("password_confirm", "Test1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("")));
    }

    @Test
    @DisplayName("Check authentication required")
    @Story("Authentication Controller")
    @Description("Unauthorized user cannot open login/success page and will be redirected to login page")
    void checkAuthority() throws Exception {
        this.mockMvc.perform(get("/login/success"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("[Correct] Check correct login")
    @Story("Authentication Controller")
    @Description("Check that system will log in user with correct credentials")
    void login_correct_credentials() throws Exception {
        this.mockMvc
                .perform(formLogin()
                        .user("testAdmin")
                        .password("Test1234")).andDo(print())
                .andExpect(redirectedUrl("/login/success"));
    }

    @Test
    @DisplayName("[Wrong] Check wrong login")
    @Story("Authentication Controller")
    @Description("Check that system won`t log in user with wrong credentials")
    void login_wrong_credentials() throws Exception {
        this.mockMvc
                .perform(formLogin()
                        .user("admin")
                        .password("IncorrectPass12345")).andDo(print())
                .andExpect(redirectedUrl("/login/fail"));
    }
}