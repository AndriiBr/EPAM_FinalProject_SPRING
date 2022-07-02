package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;

import java.util.List;

public interface IGenreService {

    /**
     * Get list of all genres from DB
     * @return list of genre entities
     */
    List<Genre> findAllGenres();
}
