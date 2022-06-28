package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Role;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping
public class AuthenticationController {

    private final UserRepo userRepo;

    @Autowired
    public AuthenticationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/registration")
    public String openRegistrationForm() {
        return "login_page/registrationForm";
    }

    @GetMapping("/registration/fail")
    public String openRegistrationFailPage() {
        return "login_page/registrationFail";
    }

    @GetMapping("/registration/success")
    public String openRegistrationSuccessPage() {
        return "login_page/registrationSuccess";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            String message = String.format("User %s is already exists!", user.getUsername());
            model.addAttribute("errorMessage", message);
            return "login_page/registrationFail";
        }

        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
