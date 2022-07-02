package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Role;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserService;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("[Unit] Service")
@Feature("User Service")
class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;

    private final User user;
    private final Page<User> page;

    UserServiceTest() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("testUser1");
        user.setRoles(new HashSet<>(Collections.singleton(Role.ROLE_USER)));

        List<User> userList = Arrays.asList(new User(), new User());
        page = new PageImpl<>(userList, PageRequest.of(0, 5), 2);
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(userRepo.save(any())).thenReturn(user);
        Mockito.when(userRepo.findAll(any(Pageable.class))).thenReturn(page);
    }

    @Test
    @DisplayName("Find user in DB by username")
    @Story("User service")
    void findUserByUsername() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(user);
        assertEquals("testUser1", userService.findUserByUsername("testUser1").getUsername());
    }

    @Test
    @DisplayName("Top up user balance")
    @Story("User service")
    void topUpBalance() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(user);
        userService.topUpBalance(user, "200");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    @DisplayName("Update user role")
    @Story("User service")
    void updateUserRole() {
        userService.updateUserRole(user);
        assertTrue(user.getRoles().contains(Role.ROLE_BLOCKED));

        userService.updateUserRole(user);
        assertTrue(user.getRoles().contains(Role.ROLE_USER));

        Mockito.verify(userRepo, Mockito.times(2)).save(user);
    }

    @Test
    @DisplayName("Get page of users")
    @Story("User service")
    void getUsersPage() {
        assertEquals(page, userService.getUsersPage("1", "2"));
    }

    @Test
    @DisplayName("[Success] Save new user in DB")
    @Story("User service")
    void saveNewUser() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(null);
        userService.saveNewUser("testUser2", "testUser2@test.com", "Test1234", "Test1234");

        Mockito.verify(userRepo, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("[User already exist message] Save new user in DB")
    @Story("User service")
    void saveNewUser_already_exist() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(user);

        assertEquals("User testUser1 is already exists!", userService.saveNewUser(
                "testUser1", "test@test.com", "Test1234", "Test1234"));
    }

    @Test
    @DisplayName("[Wrong input message] Save new user in DB")
    @Story("User service")
    void saveNewUser_wrong_input() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(null);

        assertEquals("Wrong input data", userService.saveNewUser(
                "testUser1", "test@test.com", "Test1234", "Test5674"));
    }
}