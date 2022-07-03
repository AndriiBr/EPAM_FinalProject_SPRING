package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Role;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
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
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private EditionService editionService;

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open user list page")
    @Story("Admin Controller")
    @Description("Open admin`s user list page")
    void openUserListPage() throws Exception {
        this.mockMvc.perform(get("/admin/user-list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("userList", notNullValue()));
    }

    @Test
    @WithUserDetails("testUser1")
    @DisplayName("[Access denied] Open user list page")
    @Story("Admin Controller")
    @Description("Prevent a user without admin role to open user list")
    void openUserListPage_denied() throws Exception {
        this.mockMvc.perform(get("/admin/user-list"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open edition list page")
    @Story("Admin Controller")
    @Description("Open admin`s edition list page")
    void openEditionListPage() throws Exception {
        this.mockMvc.perform(get("/admin/edition"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("editionList", notNullValue()))
                .andExpect(model().attribute("genreList", notNullValue()));
    }

    @Test
    @WithUserDetails("testUser1")
    @DisplayName("[Access denied] Open edition list page")
    @Story("Admin Controller")
    @Description("Prevent a user without admin role to open admin`s edition list page")
    void openEditionListPage_denied() throws Exception {
        this.mockMvc.perform(get("/admin/edition"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open new edition form")
    @Story("Admin Controller")
    @Description("Open new edition form")
    void openNewEditionPage() throws Exception {
        this.mockMvc.perform(get("/admin/edition/new-edition"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("genreList", notNullValue()));
    }

    @Test
    @WithUserDetails("testUser1")
    @DisplayName("[Access denied] Open new edition form")
    @Story("Admin Controller")
    @Description("Open new edition form")
    void openNewEditionPage_denied() throws Exception {
        this.mockMvc.perform(get("/admin/edition/new-edition"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Add new edition")
    @Story("Admin Controller")
    @Description("Add new edition from admin console")
    void addNewEdition() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders
                .multipart("/admin/edition/new-edition")
                .file("file-name", "12345".getBytes(StandardCharsets.UTF_8))
                .param("title_en", "Test title EN 1")
                .param("title_ua", "Test title UA 1")
                .param("text_en", "Test text EN 1")
                .param("text_ua", "Test text UA 1")
                .param("price", "100")
                .param("genre", "1");

        Page<Edition> listBefore = editionService.findAll("1", "10", null, null);
        int elementsBefore = listBefore.getNumberOfElements();

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/admin/edition"));

        Page<Edition> listAfter = editionService.findAll("1", "10", null, null);
        int elementsAfter = listAfter.getNumberOfElements();

        assertEquals(elementsBefore, elementsAfter - 1);
    }

    @Test
    @WithUserDetails("testUser1")
    @DisplayName("[Access denied] Add new edition")
    @Story("Admin Controller")
    @Description("Only user with admin rights can add new edition from admin console")
    void addNewEdition_denied() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders
                .multipart("/admin/edition/new-edition")
                .file("file-name", "12345".getBytes(StandardCharsets.UTF_8))
                .param("title_en", "Test title EN 2")
                .param("title_ua", "Test title UA 2")
                .param("text_en", "Test text EN 2")
                .param("text_ua", "Test text UA 2")
                .param("price", "200")
                .param("genre", "2");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Delete edition")
    @Story("Admin Controller")
    @Description("Delete edition from list and all user-edition pair from user_edition table")
    void deleteEdition() throws Exception {
        User user = userService.findUserByUsername("testAdmin");
        Page<Edition> listOwnerBefore = editionService.findAllOrdered(user, "1", "10", null, null);
        int elementsOwnerBefore = listOwnerBefore.getNumberOfElements();

        Page<Edition> listAllBefore = editionService.findAll("1", "10", null, null);
        int elementsAllBefore = listAllBefore.getNumberOfElements();

        this.mockMvc.perform(post("/admin/edition/delete")
                        .param("delete_edition_id", "3"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/edition"));

        Page<Edition> listOwnerAfter = editionService.findAllOrdered(user, "1", "10", null, null);
        int elementsOwnerAfter = listOwnerAfter.getNumberOfElements();

        Page<Edition> listAllAfter = editionService.findAll("1", "10", null, null);
        int elementsAllAfter = listAllAfter.getNumberOfElements();

        assertEquals(elementsOwnerBefore, elementsOwnerAfter + 1);
        assertEquals(elementsAllBefore, elementsAllAfter + 1);
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open edit edition form")
    @Story("Admin Controller")
    void openEditionEditPage() throws Exception {
        this.mockMvc.perform(get("/admin/edition/edit")
                        .param("edit_edition_id", "2"))
                .andDo(print())
                .andExpect(model().attribute("editEdition", notNullValue()))
                .andExpect(model().attribute("genreList", notNullValue()))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Open edit edition form")
    @Story("Admin Controller")
    void updateEdition() throws Exception {
        MockHttpServletRequestBuilder multipart = MockMvcRequestBuilders
                .multipart("/admin/edition/edit")
                .file("file-name", "12345678".getBytes(StandardCharsets.UTF_8))
                .param("edit_edition_id", "2")
                .param("title_en", "Test Edition 2-updated")
                .param("title_ua", "Тестове Видання 2-updated")
                .param("text_en", "Test Text TEXT 2-updated")
                .param("text_ua", "Тестовий текст ТЕКСТ 2-updated")
                .param("price", "999")
                .param("genre", "2");

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/admin/edition"));

        List<Edition> content = editionService.findAll("1", "10", null, null).getContent();
        Edition updatedEdition = content.stream()
                .filter(edition -> edition.getPrice() == 999
                        && edition.getTitleEn().equals("Test Edition 2-updated")
                        && edition.getTitleUa().equals("Тестове Видання 2-updated")
                        && edition.getTextEn().equals("Test Text TEXT 2-updated")
                        && edition.getTextUa().equals("Тестовий текст ТЕКСТ 2-updated")
                        && edition.getGenre().getId() == 2)
                .findFirst()
                .orElse(null);

        assertNotNull(updatedEdition);
    }

    @Test
    @WithUserDetails("testAdmin")
    @DisplayName("[Access granted] Block/unblock user")
    @Story("Admin Controller")
    void userBlockUnblock() throws Exception {
        User userBefore = userService.findUserByUsername("testUser1");
        Set<Role> roleListBefore = userBefore.getRoles();

        this.mockMvc.perform(post("/admin/user-list/block").param("user_block", "2"))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/user-list"));

        User userAfter = userService.findUserByUsername("testUser1");
        Set<Role> roleListAfter = userAfter.getRoles();

        assertTrue(roleListBefore.contains(Role.ROLE_USER) && !roleListBefore.contains(Role.ROLE_BLOCKED));
        assertTrue(roleListAfter.contains(Role.ROLE_BLOCKED) && !roleListAfter.contains(Role.ROLE_USER));
    }
}