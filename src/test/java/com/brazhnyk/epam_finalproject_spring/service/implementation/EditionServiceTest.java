package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@DisplayName("[Unit] Service")
@Feature("Edition Service")
class EditionServiceTest {

    @Mock
    private EditionRepo editionRepo;
    @InjectMocks
    private EditionService editionService;

    private final Page<Edition> page;
    private final User user;

    EditionServiceTest() {
        MockitoAnnotations.openMocks(this);

        List<Edition> editionList = Arrays.asList(new Edition(), new Edition(), new Edition());
        page = new PageImpl<>(editionList, PageRequest.of(0, 5), 3);

        user = new User();
        user.setId(1L);
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(editionRepo.findAllWithPagination(any(Pageable.class))).thenReturn(page);
        Mockito.when(editionRepo.findAllByGenre(any(Genre.class), any(Pageable.class))).thenReturn(page);
        Mockito.when(editionRepo.save(any())).thenReturn(new Edition());
        Mockito.doNothing().when(editionRepo).delete(any());
    }

    @Test
    @DisplayName("[With genre] Find all with genre filter")
    @Story("Edition Service")
    void findAll_with_genre() {
        editionService.findAll("1", "5", new Genre(), "price");
        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByGenre(any(Genre.class), any(Pageable.class));

        assertEquals(3, editionService.findAll("1", "5", new Genre(), "price").getTotalElements());
    }

    @Test
    @DisplayName("[Without genre] Find all without genre filter")
    @Story("Edition Service")
    void findAll_without_genre() {
        editionService.findAll("1", "5", null, "price");
        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllWithPagination(any(Pageable.class));

        assertEquals(3, editionService.findAll("1", "5", null, "price").getTotalElements());
    }

    @Test
    @DisplayName("[Not null] Save edition in DB")
    @Story("Edition Service")
    void saveEdition() {
        editionService.saveEdition(new Edition());
        Mockito.verify(editionRepo, Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("[Null] Save edition in DB")
    @Story("Edition Service")
    void saveEdition_null() {
        editionService.saveEdition(null);
        Mockito.verify(editionRepo, Mockito.times(0)).save(any());

    }

    @Test
    @DisplayName("[Not null] Delete edition from DB")
    @Story("Edition Service")
    void delete() {
        editionService.deleteEdition(new Edition());
        Mockito.verify(editionRepo, Mockito.times(1)).delete(any());
    }

    @Test
    @DisplayName("[Null] Delete edition from DB")
    @Story("Edition Service")
    void delete_null() {
        editionService.deleteEdition(null);
        Mockito.verify(editionRepo, Mockito.times(0)).delete(any());
    }

    @Test
    @DisplayName("[Default] Find not ordered editions")
    @Story("Edition Service")
    void findAllNotOrdered() {
        Mockito.when(editionRepo.findAllByGenreAndUserIdNotIn(any(Genre.class), anyLong(), any(Pageable.class)))
                .thenReturn(page);

        assertEquals(3, editionService
                .findAllNotOrdered(user, "1", "3", new Genre(), null)
                .getTotalElements());

        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByGenreAndUserIdNotIn(new Genre(), user.getId(), PageRequest.of(0, 3));
    }

    @Test
    @DisplayName("[Null genre] Find not ordered editions")
    @Story("Edition Service")
    void findAllNotOrdered_null_genre() {
        Mockito.when(editionRepo.findAllByUserIdNotIn(anyLong(), any(Pageable.class)))
                .thenReturn(page);

        assertEquals(3, editionService
                .findAllNotOrdered(user, "1", "3", null, null)
                .getTotalElements());

        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByUserIdNotIn(user.getId(), PageRequest.of(0, 3));
    }

    @Test
    @DisplayName("[Null user] Find not ordered editions")
    @Story("Edition Service")
    void findAllNotOrdered_null_user() {
        Mockito.when(editionRepo.findAllByGenre(any(Genre.class), any(Pageable.class)))
                .thenReturn(page);

        assertEquals(3, editionService
                .findAllNotOrdered(null, "1", "3", new Genre(), null)
                .getTotalElements());

        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByGenre(new Genre(), PageRequest.of(0, 3));
    }

    @Test
    @DisplayName("[Null user/genre] Find not ordered editions")
    @Story("Edition Service")
    void findAllNotOrdered_null_user_genre() {
        Mockito.when(editionRepo.findAllWithPagination(any(Pageable.class)))
                .thenReturn(page);

        assertEquals(3, editionService
                .findAllNotOrdered(null, "1", "3", null, null)
                .getTotalElements());

        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllWithPagination(PageRequest.of(0, 3));
    }

    @Test
    @DisplayName("[Default] Find ordered editions")
    @Story("Edition Service")
    void findAllOrdered() throws AuthenticationError {
        Mockito.when(editionRepo.findAllByGenreAndUserIdIn(any(Genre.class), anyLong(), any(Pageable.class))).thenReturn(page);
        editionService.findAllOrdered(user, "1", "3", new Genre(), null);

        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByGenreAndUserIdIn(new Genre(), user.getId(), PageRequest.of(0, 3));

        assertEquals(3, editionService
                .findAllOrdered(user, "1", "3", new Genre(), null)
                .getTotalElements());
    }

    @Test
    @DisplayName("[Null genre] Find ordered editions")
    @Story("Edition Service")
    void findAllOrdered_null_genre() throws AuthenticationError {
        Mockito.when(editionRepo.findAllByUserIdIn(anyLong(), any(Pageable.class))).thenReturn(page);
        editionService.findAllOrdered(user, "1", "3", null, null);
        Mockito.verify(editionRepo, Mockito.times(1))
                .findAllByUserIdIn(1L, PageRequest.of(0, 3));
        assertEquals(3, editionService
                .findAllOrdered(user, "1", "3", null, null)
                .getTotalElements());
    }

    @Test
    @DisplayName("[Null user] Find ordered editions")
    @Story("Edition Service")
    void findAllOrdered_null_user() throws AuthenticationError {
        assertThrows(AuthenticationError.class, () ->
                editionService.findAllOrdered(null, "1", "3", null, null));
    }
}