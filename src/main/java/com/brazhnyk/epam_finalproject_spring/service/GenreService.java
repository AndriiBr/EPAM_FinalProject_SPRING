package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepo genreRepo;

    @Autowired
    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    public List<Genre> findAllGenres() {
        return genreRepo.findAll();
    }
}
