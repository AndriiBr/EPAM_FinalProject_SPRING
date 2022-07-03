package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.service.IUserService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AuthenticationController {

    private final IUserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN', 'ROLE_BLOCKED')")
    @GetMapping("/login/success")
    public String openLoginSuccessPage() {
        return "login_page/loginSuccess";
    }

    @GetMapping("/login/fail")
    public String openLoginFailPage() {
        return "login_page/loginFail";
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
    public String addNewUser(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("password_confirm") String passwordConfirm,
                             Model model) {
        String result = userService.saveNewUser(username, email, password, passwordConfirm);

        if (!result.isEmpty()) {
            model.addAttribute("errorMessage", result);
            return "login_page/registrationFail";
        }

        return "redirect:/registration/success";
    }
}
