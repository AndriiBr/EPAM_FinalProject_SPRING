package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionService {

    private final EditionRepo editionRepo;

    @Autowired
    public EditionService(EditionRepo editionRepo) {
        this.editionRepo = editionRepo;
    }

    public List<Edition> findAll() {
        return editionRepo.findAll();
    }

    public void saveEdition(Edition edition) {
        if (edition != null) {
            editionRepo.save(edition);
        }
    }

    public Edition updateEdition(Edition editionFromDb, Edition newEdition) {
        BeanUtils.copyProperties(newEdition, editionFromDb, "id");

        Edition updatedEdition = editionRepo.save(newEdition);

        return updatedEdition;
    }

    public void delete(Edition edition) {
        editionRepo.delete(edition);
    }
}
