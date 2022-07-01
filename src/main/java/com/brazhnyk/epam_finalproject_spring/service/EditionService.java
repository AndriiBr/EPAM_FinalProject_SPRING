package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import com.brazhnyk.epam_finalproject_spring.util.InputValidator;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

import static com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine.CURRENT_PAGE;
import static com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine.RECORDS_PER_PAGE;

@Service
public class EditionService {

    private final EditionRepo editionRepo;

    @Autowired
    public EditionService(EditionRepo editionRepo) {
        this.editionRepo = editionRepo;
    }

    public Page<Edition> findAll(String page, String records,Genre genre, String orderBy) {

        Pageable pageable = PaginationPresetEngine.definePageableByParam(page, records, orderBy);

        if (genre == null) {
            return editionRepo.findAll(pageable);
        } else {
            return editionRepo.findAllByGenre(genre, pageable);
        }
    }

    public void saveEdition(Edition edition) {
        if (edition != null) {
            editionRepo.save(edition);
        }
    }

    public Edition updateEdition(Edition editionFromDb, Edition newEdition) {
        BeanUtils.copyProperties(newEdition, editionFromDb, "id");

        return editionRepo.save(newEdition);
    }

    public void delete(Edition edition) {
        editionRepo.delete(edition);
    }

    public Optional<Edition> findById(Long editionId) {
        return editionRepo.findById(editionId);
    }

    public Page<Edition> getEditionPage(String page, String records) {
        Pageable pageable = PaginationPresetEngine.definePageableByParam(page, records, null);

        return editionRepo.findAll(pageable);
    }

    /**
     * Generates page with editions using provided filters.
     * @param user - logged in user
     * @param page - number of required page
     * @param records - number of records per page
     * @param genre - genreFilter
     * @return page with editions
     */
    public Page<Edition> findAllNotOrdered(User user, String page, String records, Genre genre, String orderBy) {

        Pageable pageable = PaginationPresetEngine.definePageableByParam(page, records, orderBy);


        if (genre == null) {
            if (user == null) {
                return editionRepo.findAll(pageable);
            } else {
                return editionRepo.findAllByUserIdNotIn(user.getId(), pageable);
            }

        } else {
            if (user == null) {
                return editionRepo.findAllByGenre(genre, pageable);
            } else {
                return editionRepo.findAllByGenreAndUserIdNotIn(genre, user.getId(), pageable);
            }
        }
    }

    public Page<Edition> findAllOrdered(User user, String page, String records,Genre genre, String orderBy) throws AuthenticationError {

        Pageable pageable = PaginationPresetEngine.definePageableByParam(page, records, orderBy);

        if (user == null) {
            throw new AuthenticationError();
        } else {
            if (genre == null) {
                return editionRepo.findAllByUserIdIn(user.getId(), pageable);
            } else {
                return editionRepo.findAllByGenreAndUserIdIn(genre, user.getId(), pageable);
            }
        }
    }
}
