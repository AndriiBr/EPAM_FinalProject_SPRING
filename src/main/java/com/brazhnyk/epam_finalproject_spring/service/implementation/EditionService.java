package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import com.brazhnyk.epam_finalproject_spring.service.IEditionService;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditionService implements IEditionService {

    private final EditionRepo editionRepo;

    @Autowired
    public EditionService(EditionRepo editionRepo) {
        this.editionRepo = editionRepo;
    }

    @Override
    public Page<Edition> findAll(String page, String records,Genre genre, String orderBy) {

        Pageable pageable = PaginationPresetEngine.definePageableByParam(page, records, orderBy);

        if (genre == null) {
            return editionRepo.findAll(pageable);
        } else {
            return editionRepo.findAllByGenre(genre, pageable);
        }
    }

    @Override
    public void saveEdition(Edition edition) {
        if (edition != null) {
            editionRepo.save(edition);
        }
    }

    @Override
    public void deleteEdition(Edition edition) {
        if (edition != null) {
            editionRepo.delete(edition);
        }
    }

    @Override
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

    @Override
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
