package com.brazhnyk.epam_finalproject_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("index")
    public String getIndexPage(@RequestParam(value = "name", required = false) String name,
                               Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("test")
    public String getTestPage() {
        return "test";
    }
}
