package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.User;
import org.springframework.data.domain.Page;

public interface IUserService {

    /**
     * Find user in DB
     * @param username - username
     * @return user entity
     */
    User findUserByUsername(String username);

    /**
     * Top up user balance
     * @param user - user entity
     * @param money - amount of money
     */
    void topUpBalance(User user, String money);

    /**
     * Block/Unblock provided user
     * @param user - user entity
     */
    void updateUserRole(User user);

    /**
     * Prepare page pf users
     * @param page - page number
     * @param records - records per page
     * @return page pf users
     */
    Page<User> getUsersPage(String page, String records);

    /**
     * Save new user in DB
     * @param username - username
     * @param email - email
     * @param password - password
     * @param passwordConfirm - password confirmation
     * @return error message. Or empty if success
     */
    String saveNewUser(String username, String email, String password, String passwordConfirm);
}
