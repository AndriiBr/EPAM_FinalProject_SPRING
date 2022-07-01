package com.brazhnyk.epam_finalproject_spring.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Utility class to maintain the preparation required for the pagination panel.
 */
public class PaginationPresetEngine {

    public static final int RECORDS_PER_PAGE = 5;
    public static final int CURRENT_PAGE = 1;

    private PaginationPresetEngine() {
    }

    /**
     * Create Pageable entity based on provided parameters and current locale;
     * @param page - number of page
     * @param records - records per page
     * @param orderBy - sort parameter. Put null if no sorting required.
     * @return Pageable entity configured by provided params
     */
    public static Pageable definePageableByParam(String page, String records, String orderBy) {
        int currentPage = InputValidator.validateNumberValue(page) ? Integer.parseInt(page) : CURRENT_PAGE;
        int recordsPerPage = InputValidator.validateNumberValue(records) ? Integer.parseInt(records) : RECORDS_PER_PAGE;

        Locale locale = LocaleContextHolder.getLocale();
        String lang = locale.getLanguage();

        Pageable pageableResult = PageRequest.of(currentPage - 1, recordsPerPage);

        if (orderBy != null) {
            if (orderBy.equals("price")) {
                pageableResult = PageRequest.of(currentPage - 1, recordsPerPage, Sort.by("price").ascending());
            } else if (orderBy.equals("title")) {
                if (lang.equals("ua")) {
                    pageableResult = PageRequest.of(currentPage - 1, recordsPerPage, Sort.by("edition.title_ua").ascending());
                } else {
                    pageableResult = PageRequest.of(currentPage - 1, recordsPerPage, Sort.by("edition.title_en").ascending());
                }
            }
        }

        return pageableResult;
    }

    /**
     * Fulfill model with parameters for pagination bar.
     * @param model - model to be updated
     * @param page - collection of elements from DB.
     * @param currentPage - number of current page
     * @param recordsPerPage - number of records per page
     * @param <T> - entity type in collection from DB.
     */
    public static <T> void updateModelForPagination(Model model, Page<T> page, String currentPage, String recordsPerPage) {
        model.addAttribute("pageNumbers", preparePageNumbers(page));
        model.addAttribute("itemStep", prepareItemStep(3, 5, 7, 10));
        model.addAttribute("totalPages", preparePageNumbers(page).size());

        model.addAttribute("currentPage", currentPage != null ? currentPage : CURRENT_PAGE);
        model.addAttribute("recordsPerPage", recordsPerPage != null ? recordsPerPage : RECORDS_PER_PAGE);
    }

    /**
     * Prepare List of Integers for pagination bar ->
     * provides different variants how many records will be rendered at the final page
     * @param values - variants to be prepared
     * @return list of variants
     */
    private static List<Integer> prepareItemStep(int... values) {
        return Arrays.stream(values).boxed().collect(Collectors.toList());
    }

    /**
     * Prepare List of Integers for pagination bar ->
     * provides available page numbers from 1 to "totalPages" from Page entity
     * @param page - Page entity
     * @return list of page numbers. If page is empty - than return list with 1
     */
    private static  <T> List<Integer> preparePageNumbers(Page<T> page) {
        int totalPages = page.getTotalPages() > 0 ? page.getTotalPages() : 1;

        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }
}
