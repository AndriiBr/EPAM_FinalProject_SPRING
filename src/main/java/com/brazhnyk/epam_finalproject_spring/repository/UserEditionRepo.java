package com.brazhnyk.epam_finalproject_spring.repository;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.entity.UserEdition;
import com.brazhnyk.epam_finalproject_spring.entity.UserEditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserEditionRepo extends JpaRepository<UserEdition, UserEditionId> {

    @Transactional
    Integer deleteAllByUserAndEdition(User user, Edition edition);
}
