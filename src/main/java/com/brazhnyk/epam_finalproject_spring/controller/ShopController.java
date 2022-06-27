package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import com.brazhnyk.epam_finalproject_spring.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping
public class ShopController {

    @Value("${spring.profiles.active}")
    private String profile;

    private final EditionRepo editionRepo;
    private final GenreRepo genreRepo;

    @Autowired
    public ShopController(EditionRepo editionRepo, GenreRepo genreRepo) {
        this.editionRepo = editionRepo;
        this.genreRepo = genreRepo;
    }

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal User user,
                              Model model) {
        List<Edition> editionList = editionRepo.findAll();
        List<Genre> genreList = genreRepo.findAll();
        model.addAttribute("editionList", editionList);
        model.addAttribute("genreList", genreList);

        return "edition_page/mainEditions";
    }

    @GetMapping("/buy")
    public String openBuyPage(@RequestParam(name = "buy_edition_id") Long editionId, Model model) {
        Edition edition = editionRepo.findById(editionId).get();

        model.addAttribute("edition", edition);

        return "edition_page/buyEdition";
    }
}
