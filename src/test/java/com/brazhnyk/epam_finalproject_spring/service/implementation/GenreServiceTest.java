package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.repository.GenreRepo;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit] Service")
@Feature("Genre Service")
class GenreServiceTest {

    @Mock
    private GenreRepo genreRepo;
    @InjectMocks
    private GenreService genreService;

    GenreServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find all genres in DB")
    @Story("Genre Service")
    void findAllGenres() {
        Mockito.when(genreRepo.findAll()).thenReturn(Arrays.asList(new Genre(), new Genre()));

        assertEquals(2, genreService.findAllGenres().size());

        Mockito.verify(genreRepo, Mockito.times(1)).findAll();
    }
}