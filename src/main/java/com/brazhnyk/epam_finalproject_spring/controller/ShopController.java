package com.brazhnyk.epam_finalproject_spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("shop")
public class ShopController {


    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping("/list")
    public String getMainPage(Model model) {

        model.addAttribute("isDevMode", "dev".equals(profile));
        model.addAttribute("test", "Does this stupid shit works?");
        return "index";
    }
}
