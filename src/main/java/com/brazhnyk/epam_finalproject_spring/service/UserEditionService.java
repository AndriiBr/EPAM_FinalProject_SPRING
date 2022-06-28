package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.repository.UserEditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEditionService {

    private final UserEditionRepo userEditionRepo;

    @Autowired
    public UserEditionService(UserEditionRepo userEditionRepo) {
        this.userEditionRepo = userEditionRepo;
    }
}
