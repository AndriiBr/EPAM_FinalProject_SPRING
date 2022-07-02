package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.entity.UserEdition;
import com.brazhnyk.epam_finalproject_spring.repository.UserEditionRepo;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("[Unit] Service")
@Feature("UserEdition Service")
class UserEditionServiceTest {

    @Mock
    private UserRepo userRepo;
    @Mock
    private UserEditionRepo userEditionRepo;
    @InjectMocks
    private UserEditionService userEditionService;

    private final User user;
    private final Edition edition1;
    private final Edition edition2;

    UserEditionServiceTest() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setBalance(100);

        edition1 = new Edition();
        edition1.setPrice(50);

        edition2 = new Edition();
        edition2.setPrice(1000);
    }

    @Test
    @DisplayName("Unsubscribe from edition")
    @Story("UserEdition Service")
    void unsubscribe() {
        Mockito.when(userEditionRepo.deleteAllByUserAndEdition(any(User.class), any(Edition.class))).thenReturn(1);
        userEditionService.unsubscribe(new User(), new Edition());
        Mockito.verify(userEditionRepo, Mockito.times(1))
                .deleteAllByUserAndEdition(new User(), new Edition());
    }

    @Test
    @DisplayName("[Success] Buy edition")
    @Story("UserEdition Service")
    void buyNewEdition_success() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(user);
        Mockito.when(userRepo.save(any(User.class))).thenReturn(user);
        Mockito.when(userEditionRepo.save(any(UserEdition.class))).thenReturn(new UserEdition(user, edition1));

        userEditionService.buyNewEdition(user, edition1);

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(userEditionRepo, Mockito.times(1)).save(new UserEdition(user, edition1));
    }

    @Test
    @DisplayName("[Fail] Buy edition")
    @Story("UserEdition Service")
    void buyNewEdition_fail() {
        Mockito.when(userRepo.findByUsername(any())).thenReturn(user);
        Mockito.when(userRepo.save(any(User.class))).thenReturn(user);
        Mockito.when(userEditionRepo.save(any(UserEdition.class))).thenReturn(new UserEdition(user, edition1));

        userEditionService.buyNewEdition(user, edition2);

        Mockito.verify(userRepo, Mockito.times(0)).save(user);
        Mockito.verify(userEditionRepo, Mockito.times(0)).save(new UserEdition(user, edition1));
    }
}