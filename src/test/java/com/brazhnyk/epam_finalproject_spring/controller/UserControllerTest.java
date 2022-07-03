package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
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
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private EditionService editionService;

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open wallet page")
    @Story("User Controller")
    @Description("Open wallet page if user is authorized")
    void openWalletPage_authorized() throws Exception {
        this.mockMvc
                .perform(get("/user/wallet"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[Not authorized] Open wallet page")
    @Story("User Controller")
    @Description("Redirect to login page if user not authorized in the system")
    void openWalletPage_not_authorized() throws Exception {
        this.mockMvc
                .perform(get("/user/wallet"))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open top up wallet page")
    @Story("User Controller")
    @Description("Open wallet top up page if user is authorized")
    void openWalletTopUpPage_authorized() throws Exception {
        this.mockMvc
                .perform(get("/user/wallet/top_up"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model()
                        .attribute("current_balance", notNullValue()));
    }

    @Test
    @DisplayName("[Not authorized] Open top up wallet page")
    @Story("User Controller")
    @Description("Open wallet top up page if user is authorized")
    void openWalletTopUpPage_not_authorized() throws Exception {
        this.mockMvc
                .perform(get("/user/wallet/top_up"))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Top up balance")
    @Story("User Controller")
    @Description("Authorized user can successfully top up their balance")
    void topUpWallet() throws Exception {
        User userBeforeTopUp = userService.findUserByUsername("testAdmin");
        int startBalance = userBeforeTopUp.getBalance();
        String money = "200";

        this.mockMvc.perform(post("/user/wallet/top_up")
                        .param("money", money))
                .andDo(print())
                .andExpect(redirectedUrl("/user/wallet"));

        User userAfterTopUp = userService.findUserByUsername("testAdmin");
        int finalBalance = userAfterTopUp.getBalance();
        int difference = finalBalance - startBalance;

        assertEquals(difference, Integer.parseInt(money));
    }

    @Test
    @WithUserDetails("testUser2")
    @DisplayName("[Access denied] Top up balance")
    @Story("User Controller")
    @Description("Prevent a user from topping up their balance. 404 error due to access denied")
    void topUpWallet_block() throws Exception {
        String money = "200";

        this.mockMvc.perform(post("/user/wallet/top_up")
                        .param("money", money))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open subscription list")
    @Story("User Controller")
    @Description("Allow a user to open their subscriptions list")
    void openSubscriptionsPage() throws Exception {
        this.mockMvc.perform(get("/user/subscriptions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("genreList", notNullValue()))
                .andExpect(model().attribute("editionList", notNullValue()));
    }

    @Test
    @DisplayName("[Not authorized] Open subscription list")
    @Story("User Controller")
    @Description("Prevent a user from opening their subscriptions list. 404 error due to access denied")
    void openSubscriptionsPage_not_authorized() throws Exception {
        this.mockMvc.perform(get("/user/subscriptions"))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Unsubscribe from edition")
    @Story("User Controller")
    @Description("Allow a user to unsubscribe from edition. Check that list will have -1 element after unsubscribing")
    void unsubscribe() throws Exception {
        User user = userService.findUserByUsername("testAdmin");
        Page<Edition> allOrderedBefore = editionService
                .findAllOrdered(user, "1", "10", null, null);
        int elementsBefore = allOrderedBefore.getNumberOfElements();

        this.mockMvc.perform(post("/user/subscriptions/unsubscribe")
                        .param("unsubscribe_edition_id", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("/user/subscriptions"));

        Page<Edition> allOrderedAfter = editionService
                .findAllOrdered(user, "1", "10", null, null);
        int elementsAfter = allOrderedAfter.getNumberOfElements();

        assertEquals(elementsBefore, elementsAfter + 1);
    }

    @Test
    @DisplayName("[Access denied] Unsubscribe from edition")
    @Story("User Controller")
    @Description("prevent a not authorized user to unsubscribe from edition")
    void unsubscribe_access_denied() throws Exception {
        this.mockMvc.perform(post("/user/subscriptions/unsubscribe")
                        .param("unsubscribe_edition_id", "1"))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}