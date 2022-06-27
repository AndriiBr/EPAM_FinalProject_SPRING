package com.brazhnyk.epam_finalproject_spring.repository;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    List<Genre> findAll();
}
