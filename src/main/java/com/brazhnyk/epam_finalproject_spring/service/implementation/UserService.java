package com.brazhnyk.epam_finalproject_spring.service.implementation;

import com.brazhnyk.epam_finalproject_spring.entity.Role;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import com.brazhnyk.epam_finalproject_spring.service.IUserService;
import com.brazhnyk.epam_finalproject_spring.util.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService implements UserDetailsService, IUserService {

    private static final int RECORDS_PER_PAGE = 5;
    private static final int CURRENT_PAGE = 1;
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void topUpBalance(User user, String money) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        int value = 0;

        if (InputValidator.validateMoney(money)) {
            value = Integer.parseInt(money);
        }
        userFromDb.setBalance(userFromDb.getBalance() + value);
        userRepo.save(userFromDb);
    }

    @Override
    public void updateUserRole(User user) {
        if (user != null) {
            Set<Role> newRoles = user.getRoles();
            if (newRoles.contains(Role.ROLE_USER)) {
                newRoles.remove(Role.ROLE_USER);
                newRoles.add(Role.ROLE_BLOCKED);
                user.setRoles(newRoles);
            } else if (newRoles.contains(Role.ROLE_BLOCKED)) {
                newRoles.remove(Role.ROLE_BLOCKED);
                newRoles.add(Role.ROLE_USER);
                user.setRoles(newRoles);
            }
            userRepo.save(user);
        }
    }

    @Override
    public Page<User> getUsersPage(String page, String records) {
        int currentPage = InputValidator.validateNumberValue(page) ? Integer.parseInt(page) : CURRENT_PAGE;
        int recordsPerPage = InputValidator.validateNumberValue(records) ? Integer.parseInt(records) : RECORDS_PER_PAGE;

        Pageable pageable = PageRequest.of(currentPage - 1, recordsPerPage);

        return userRepo.findAll(pageable);
    }

    @Override
    public String saveNewUser(String username, String email, String password, String passwordConfirm) {
        User userFromDb = userRepo.findByUsername(username);

        if (userFromDb != null) {
            return String.format("User %s is already exists!", username);
        }

        boolean isValid = InputValidator.validateRegistrationForm(username, email, password, passwordConfirm);

        if(isValid) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRoles(Collections.singleton(Role.ROLE_USER));

            userRepo.save(user);
        } else {
            return "Wrong input data";
        }

        return "";
    }
}
