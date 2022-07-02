package com.brazhnyk.epam_finalproject_spring.util;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("[Unit] Utils")
class PaginationPresetEngineTest {

    @Mock
    private Model model;
    private final Page<Edition> page = Page.empty();

    @Test
    @DisplayName("[Default pageable] Test create and fulfill pageable object")
    void definePageableByParam_default() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam(null, null, null);

        assertEquals(PaginationPresetEngine.CURRENT_PAGE - 1, pageable.getPageNumber());
        assertEquals(PaginationPresetEngine.RECORDS_PER_PAGE, pageable.getPageSize());
    }

    @Test
    @DisplayName("[Default pageable] Test create and fulfill pageable object")
    void definePageableByParam_with_params_1() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam("2", "7", "price");

        assertEquals(1, pageable.getPageNumber());
        assertEquals(7, pageable.getPageSize());
        assertFalse(pageable.getSort().isEmpty());
    }

    @Test
    @DisplayName("[Default pageable] Test create and fulfill pageable object")
    void definePageableByParam_with_params_2() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam("3", "15", "title");

        assertEquals(2, pageable.getPageNumber());
        assertEquals(15, pageable.getPageSize());
        assertFalse(pageable.getSort().isEmpty());
    }
}