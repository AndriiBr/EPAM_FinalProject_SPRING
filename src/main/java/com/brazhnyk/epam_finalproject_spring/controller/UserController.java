package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/wallet")
    public String openWalletPage(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        model.addAttribute("current_balance", userFromDb.getBalance());

        return "wallet/wallet";
    }

    @GetMapping("/wallet/top_up")
    public String openWalletTopUpPage(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        model.addAttribute("current_balance", userFromDb.getBalance());

        return "wallet/topUpWallet";
    }

    @PostMapping("/wallet/top_up")
    public String topUpWallet(@AuthenticationPrincipal User user, @RequestParam String money) {
        userService.topUpBalance(user, money);

        return "redirect:/user/wallet";
    }
}
