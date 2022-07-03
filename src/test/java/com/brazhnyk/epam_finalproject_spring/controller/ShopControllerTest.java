package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.service.implementation.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
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
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private EditionService editionService;

    @Test
    @DisplayName("Open main shop page")
    @Story("Shop Controller")
    @Description("Open main shop page with any access")
    void getMainPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("genreList", notNullValue()))
                .andExpect(model().attribute("editionList", notNullValue()));
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open buy edition page")
    @Story("Shop Controller")
    @Description("Open buy edition page with access check and edition exist")
    void openBuyPage() throws Exception {
        this.mockMvc.perform(get("/buy")
                        .param("buy_edition_id", "4"))
                .andDo(print())
                .andExpect(model().attribute("edition", notNullValue()))
                .andExpect(model().attribute("current_balance", notNullValue()))
                .andExpect(model().attribute("remainingBalance", notNullValue()))
                .andExpect(model().attribute("buy_edition_id", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("testUser2")
    @DisplayName("[Access denied] Prevent to open buy page")
    @Story("Shop Controller")
    @Description("Redirect to main page if edition id was wrong")
    void openBuyPage_wrong_id() throws Exception {
        this.mockMvc.perform(get("/buy")
                        .param("buy_edition_id", "4"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Buy edition")
    @Story("Shop Controller")
    @Description("Buy edition with access check and edition exist")
    void buyEdition() throws Exception {
        User userBefore = userService.findUserByUsername("testAdmin");
        Page<Edition> pageBefore = editionService.findAllOrdered(userBefore, "1", "10", null, null);
        int elementsBefore = pageBefore.getNumberOfElements();
        int balanceBefore = userBefore.getBalance();

        this.mockMvc.perform(post("/buy")
                        .param("buy_edition_id", "4"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        User userAfter = userService.findUserByUsername("testAdmin");
        Page<Edition> pageAfter = editionService.findAllOrdered(userAfter, "1", "10", null, null);
        int elementsAfter = pageAfter.getNumberOfElements();
        int balanceAfter = userAfter.getBalance();

        assertEquals(elementsBefore, elementsAfter - 1);
        assertTrue(balanceBefore > balanceAfter);
    }

    @Test
    @WithUserDetails("testUser2")
    @DisplayName("[Access denied] Blocked user cannot buy edition")
    @Story("Shop Controller")
    @Description("Prevent a blocked user to buy any edition")
    void buyEdition_denied() throws Exception {
        this.mockMvc.perform(post("/buy")
                        .param("buy_edition_id", "4"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}