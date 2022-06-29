package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.service.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.GenreService;
import com.brazhnyk.epam_finalproject_spring.service.UserEditionService;
import com.brazhnyk.epam_finalproject_spring.service.UserService;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EditionService editionService;
    private final GenreService genreService;
    private final UserEditionService userEditionService;

    @Autowired
    public UserController(UserService userService,
                          EditionService editionService,
                          GenreService genreService,
                          UserEditionService userEditionService) {
        this.userService = userService;
        this.editionService = editionService;
        this.genreService = genreService;
        this.userEditionService = userEditionService;
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

    @GetMapping("/subscriptions")
    public String openSubscriptionsPage(@AuthenticationPrincipal User user,
                                        @RequestParam(name = "currentPage", required = false) String currentPage,
                                        @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                        @RequestParam(name = "genreFilter", required = false) Genre genreFilter,
                                        @RequestParam(name = "orderBy", required = false) String orderBy,
                                        Model model) throws AuthenticationError {

        Page<Edition> editionList = editionService.findAllOrdered(user, currentPage, recordsPerPage, genreFilter, orderBy);
        List<Genre> genreList = genreService.findAllGenres();

        model.addAttribute("editionList", editionList);
        model.addAttribute("genreList", genreList);

        model.addAttribute("pageNumbers", PaginationPresetEngine.preparePageNumbers(editionList));
        model.addAttribute("itemStep", PaginationPresetEngine.prepareItemStep(3, 5, 7, 10));
        model.addAttribute("currentPage", currentPage != null ? currentPage : 1);
        model.addAttribute("recordsPerPage", recordsPerPage != null ? recordsPerPage : 5);
        model.addAttribute("totalPages", PaginationPresetEngine.preparePageNumbers(editionList).size());

        return "edition_page/userEditions";
    }

    @PostMapping("/subscriptions/unsubscribe")
    public String unsubscribe (@AuthenticationPrincipal User user,
                               @RequestParam(name = "unsubscribe_edition_id") Edition edition) {

        Integer result = userEditionService.unsubscribe(user, edition);

        return "redirect:/user/subscriptions";
    }
}
