package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.repository.GenreRepo;
import com.brazhnyk.epam_finalproject_spring.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService {

    private final GenreRepo genreRepo;

    @Autowired
    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepo.findAll();
    }
}
