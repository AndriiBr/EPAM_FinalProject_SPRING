package com.brazhnyk.epam_finalproject_spring.service;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.entity.UserEdition;
import com.brazhnyk.epam_finalproject_spring.repository.UserEditionRepo;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserEditionService {

    private final UserRepo userRepo;
    private final UserEditionRepo userEditionRepo;

    @Autowired
    public UserEditionService(UserRepo userRepo, UserEditionRepo userEditionRepo) {
        this.userRepo = userRepo;
        this.userEditionRepo = userEditionRepo;
    }

    public Integer unsubscribe(User user, Edition edition) {
        return userEditionRepo.deleteAllByUserAndEdition(user, edition);
    }

    //ToDo
    //Test this case of transaction. Will it rollback if any operation is unsuccessful?
    @Transactional
    public void buyNewEdition(User user, Edition edition) {
        int balanceResult = user.getBalance() - edition.getPrice();

        if (balanceResult < 0) {
            return;
        }
        user.setBalance(balanceResult);

        userRepo.save(user);
        userEditionRepo.save(new UserEdition(user, edition));
    }
}
