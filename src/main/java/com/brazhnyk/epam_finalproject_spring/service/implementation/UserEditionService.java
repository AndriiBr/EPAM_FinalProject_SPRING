package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.entity.UserEdition;
import com.brazhnyk.epam_finalproject_spring.repository.UserEditionRepo;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import com.brazhnyk.epam_finalproject_spring.service.IUserEditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserEditionService implements IUserEditionService {

    private final UserRepo userRepo;
    private final UserEditionRepo userEditionRepo;

    @Autowired
    public UserEditionService(UserRepo userRepo, UserEditionRepo userEditionRepo) {
        this.userRepo = userRepo;
        this.userEditionRepo = userEditionRepo;
    }

    @Override
    public void unsubscribe(User user, Edition edition) {
        userEditionRepo.deleteAllByUserAndEdition(user, edition);
    }

    @Override
    @Transactional
    public void buyNewEdition(User user, Edition edition) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        int balanceResult = userFromDb.getBalance() - edition.getPrice();

        if (balanceResult < 0) {
            return;
        }
        user.setBalance(balanceResult);

        userRepo.save(user);
        userEditionRepo.save(new UserEdition(user, edition));
    }
}
