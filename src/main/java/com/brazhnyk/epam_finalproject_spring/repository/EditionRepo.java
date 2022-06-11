package com.brazhnyk.epam_finalproject_spring.repository;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditionRepo extends JpaRepository<Edition, String> {

}
