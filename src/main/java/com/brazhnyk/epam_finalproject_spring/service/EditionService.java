package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import com.brazhnyk.epam_finalproject_spring.util.InputValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditionService {

    private static final int RECORDS_PER_PAGE = 5;
    private static final int CURRENT_PAGE = 1;
    private final EditionRepo editionRepo;

    @Autowired
    public EditionService(EditionRepo editionRepo) {
        this.editionRepo = editionRepo;
    }

    public List<Edition> findAllEditions() {
        return editionRepo.findAll();
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

    public Page<Edition> getEditionsPage(String page, String records) {
        int currentPage = InputValidator.validateNumberValue(page) ? Integer.parseInt(page) : CURRENT_PAGE;
        int recordsPerPage = InputValidator.validateNumberValue(records) ? Integer.parseInt(records) : RECORDS_PER_PAGE;

        Pageable pageable = PageRequest.of(currentPage - 1, recordsPerPage, Sort.Direction.DESC);

        return editionRepo.findAll(pageable);
    }

    public void delete(Edition edition) {
        editionRepo.delete(edition);
    }

    public Optional<Edition> findById(Long editionId) {
        return editionRepo.findById(editionId);
    }

    public Page<Edition> getEditionPage(String page, String records) {
        int currentPage = InputValidator.validateNumberValue(page) ? Integer.parseInt(page) : CURRENT_PAGE;
        int recordsPerPage = InputValidator.validateNumberValue(records) ? Integer.parseInt(records) : RECORDS_PER_PAGE;

        Pageable pageable = PageRequest.of(currentPage - 1, recordsPerPage);

        return editionRepo.findAll(pageable);
    }
}
