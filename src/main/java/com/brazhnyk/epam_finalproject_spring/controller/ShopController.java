package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.service.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.GenreService;
import com.brazhnyk.epam_finalproject_spring.service.UserEditionService;
import com.brazhnyk.epam_finalproject_spring.service.UserService;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ShopController {

    private final UserService userService;
    private final EditionService editionService;
    private final GenreService genreService;
    private final UserEditionService userEditionService;

    @Autowired
    public ShopController(UserService userService,
                          EditionService editionService,
                          GenreService genreService,
                          UserEditionService userEditionService) {
        this.userService = userService;
        this.editionService = editionService;
        this.genreService = genreService;
        this.userEditionService = userEditionService;
    }

    @GetMapping("/")
    public String getMainPage(@AuthenticationPrincipal User user,
                              @RequestParam(name = "currentPage", required = false) String currentPage,
                              @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                              @RequestParam(name = "genreFilter", required = false) Genre genreFilter,
                              @RequestParam(name = "orderBy", required = false) String orderBy,
                              Model model) {
        Page<Edition> editionList = editionService
                .findAllNotOrdered(user, currentPage, recordsPerPage, genreFilter, orderBy);

        List<Genre> genreList = genreService.findAllGenres();

        model.addAttribute("editionList", editionList);
        model.addAttribute("genreList", genreList);

        model.addAttribute("pageNumbers", PaginationPresetEngine.preparePageNumbers(editionList));
        model.addAttribute("itemStep", PaginationPresetEngine.prepareItemStep(3, 5, 7, 10));
        model.addAttribute("currentPage", currentPage != null ? currentPage : 1);
        model.addAttribute("recordsPerPage", recordsPerPage != null ? recordsPerPage : 5);
        model.addAttribute("totalPages", PaginationPresetEngine.preparePageNumbers(editionList).size());

        if (genreFilter != null) {
            model.addAttribute("genreFilter", genreFilter);
        }

        return "edition_page/mainEditions";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/buy")
    public String openBuyPage(@AuthenticationPrincipal User user,
                              @RequestParam(name = "buy_edition_id") Edition edition, Model model) {

        if (edition != null) {

            int userBalance = userService.findUserByUsername(user.getUsername()).getBalance();
            int remainingBalance = userBalance - edition.getPrice();

            model.addAttribute("edition", edition);
            model.addAttribute("current_balance", userBalance);
            model.addAttribute("remainingBalance", remainingBalance);
            model.addAttribute("buy_edition_id", edition);

            return "edition_page/buyEdition";
        }

        return "redirect:/";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/buy")
    public String buyEdition(@AuthenticationPrincipal User user, @RequestParam(name = "buy_edition_id") Edition edition) {
       userEditionService.buyNewEdition(user, edition);

        return "redirect:/";
    }
}
