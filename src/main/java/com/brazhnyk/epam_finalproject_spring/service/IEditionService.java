package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import org.springframework.data.domain.Page;

public interface IEditionService {

    /**
     * Get page with editions using provided filters.
     * @param page - number of required page
     * @param records - number of records per page
     * @param genre - genreFilter
     * @param orderBy - orderBy sort filter
     * @return page with editions
     */
    Page<Edition> findAll(String page, String records, Genre genre, String orderBy);

    /**
     * Save edition in DB
     * @param edition - edition entity to be saved
     */
    void saveEdition(Edition edition);

    /**
     * Delete edition from DB.
     * @param edition - edition to be deleted.
     */
    void deleteEdition(Edition edition);

    /**
     * Get a page with editions that do not belong to the user, using the provided filters.
     * @param user - logged in user
     * @param page - number of required page
     * @param records - number of records per page
     * @param genre - genreFilter
     * @return page with editions
     */
    Page<Edition> findAllNotOrdered(User user, String page, String records, Genre genre, String orderBy);

    /**
     * Get a page with editions that belong to the user, using the provided filters.
     * @param user - logged in user
     * @param page - number of required page
     * @param records - number of records per page
     * @param genre - genreFilter
     * @param orderBy - orderBy sort filter
     * @return page with editions
     * @throws AuthenticationError - if user not authorized
     */
    Page<Edition> findAllOrdered(User user, String page, String records,Genre genre, String orderBy) throws AuthenticationError;
}
