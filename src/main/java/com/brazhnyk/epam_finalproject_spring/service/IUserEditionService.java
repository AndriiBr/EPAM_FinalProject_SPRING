package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface IUserEditionService {

    /**
     * Remove user-edition pair from DB. Equal to unsubscribe operation.
     * @param user - owner
     * @param edition - owned edition
     */
    void unsubscribe(User user, Edition edition);

    /**
     * Add new user-edition pair into DB. Equal to subscribe operation
     * @param user - buyer
     * @param edition - bought edition
     */
    @Transactional
    void buyNewEdition(User user, Edition edition);
}
