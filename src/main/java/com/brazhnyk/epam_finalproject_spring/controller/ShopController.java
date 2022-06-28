package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.repository.EditionRepo;
import com.brazhnyk.epam_finalproject_spring.repository.GenreRepo;
import com.brazhnyk.epam_finalproject_spring.service.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class ShopController {

    @Value("${spring.profiles.active}")
    private String profile;

    private final EditionService editionService;
    private final GenreService genreService;

    @Autowired
    public ShopController(EditionService editionService, GenreService genreService) {
        this.editionService = editionService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal User user,
                              Model model) {
        List<Edition> editionList = editionService.findAllEditions();
        List<Genre> genreList = genreService.findAllGenres();
        model.addAttribute("editionList", editionList);
        model.addAttribute("genreList", genreList);

        return "edition_page/mainEditions";
    }

    @GetMapping("/buy")
    public String openBuyPage(@AuthenticationPrincipal User user,
                              @RequestParam(name = "buy_edition_id") Long editionId, Model model) {
        Optional<Edition> editionFromDb = editionService.findById(editionId);
        Edition edition = null;

        if (editionFromDb.isPresent()) {
            edition = editionFromDb.get();
        }

        if (edition != null) {

            int userBalance = user.getBalance();
            int remainingBalance = userBalance - edition.getPrice();

            model.addAttribute("edition", edition);
            model.addAttribute("current_balance", userBalance);
            model.addAttribute("remainingBalance", remainingBalance);
        }
        return "edition_page/buyEdition";
    }
}
