package com.brazhnyk.epam_finalproject_spring.repository;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditionRepo extends JpaRepository<Edition, Long> {

    List<Edition> findAll();

    @Query("select e from Edition e")
    Page<Edition> findAllWithPagination (Pageable pageable);

    @Query(
            value = "select * from edition where genre_id = ?",
            nativeQuery = true)
    Page<Edition> findAllByGenre(Genre genre, Pageable pageable);

    @Query(
            value = "select * from edition where id not in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByUserIdNotIn(Long userId, Pageable pageable);

    @Query(
            value = "select * from edition where genre_id = ? and id not in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByGenreAndUserIdNotIn(Genre genre, Long userId, Pageable pageable);

    @Query(
            value = "select * from edition where id in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByUserIdIn(Long userId, Pageable pageable);

    @Query(
            value = "select * from edition where genre_id = ? and id in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByGenreAndUserIdIn(Genre genre, Long userId, Pageable pageable);

    Page<Edition> findByTitleEnContains(String title, Pageable pageable);

    Page<Edition> findByTitleUaContains(String title, Pageable pageable);
}
