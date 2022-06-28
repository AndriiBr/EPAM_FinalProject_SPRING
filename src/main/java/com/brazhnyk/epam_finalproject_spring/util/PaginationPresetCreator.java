package com.brazhnyk.epam_finalproject_spring.util;

import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Utility class to maintain the preparation required for the pagination panel.
 */
public class PaginationPresetCreator {

    private PaginationPresetCreator() {
    }

    /**
     * Prepare List of Integers for pagination bar ->
     * provides different variants how many records will be rendered at the final page
     * @param values - variants to be prepared
     * @return list of variants
     */
    public static List<Integer> prepareItemStep(int... values) {
        return Arrays.stream(values).boxed().collect(Collectors.toList());
    }

    /**
     * Prepare List of Integers for pagination bar ->
     * provides available page numbers from 1 to "totalPages" from Page entity
     * @param page - Page entity
     * @return list of page numbers. If page is empty - than return list with 1
     */
    public static  <T> List<Integer> preparePageNumbers(Page<T> page) {
        int totalPages = page.getTotalPages() > 0 ? page.getTotalPages() : 1;

        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }
}
