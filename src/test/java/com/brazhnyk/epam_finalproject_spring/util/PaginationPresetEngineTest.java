package com.brazhnyk.epam_finalproject_spring.util;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("[Unit] Utils")
@Feature("Utils")
class PaginationPresetEngineTest {

    @Test
    @DisplayName("[Default pageable] Test create and fulfill pageable object")
    @Story("Utils pagination")
    void definePageableByParam_default() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam(null, null, null);

        assertEquals(PaginationPresetEngine.CURRENT_PAGE - 1, pageable.getPageNumber());
        assertEquals(PaginationPresetEngine.RECORDS_PER_PAGE, pageable.getPageSize());
    }

    @Test
    @DisplayName("[OrderBy price pageable] Test create and fulfill pageable object")
    @Story("Utils pagination")
    void definePageableByParam_with_params_1() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam("2", "7", "price");

        assertEquals(1, pageable.getPageNumber());
        assertEquals(7, pageable.getPageSize());
        assertFalse(pageable.getSort().isEmpty());
    }

    @Test
    @DisplayName("[OrderBy title pageable] Test create and fulfill pageable object")
    @Story("Utils pagination")
    void definePageableByParam_with_params_2() {
        Pageable pageable = PaginationPresetEngine.definePageableByParam("3", "15", "title");

        assertEquals(2, pageable.getPageNumber());
        assertEquals(15, pageable.getPageSize());
        assertFalse(pageable.getSort().isEmpty());
    }
}