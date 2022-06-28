package com.brazhnyk.epam_finalproject_spring.repository;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EditionRepo extends JpaRepository<Edition, Long> {
    List<Edition> findAll();

    @Query(
            value = "select * from edition where id not in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByUserIdNotIn(Long userId, Pageable pageable);

    @Query(
            value = "select * from edition where id in(select edition_id from user_edition where user_id = ?)",
            nativeQuery = true)
    Page<Edition> findAllByUserIdIn(Long userId, Pageable pageable);



}
