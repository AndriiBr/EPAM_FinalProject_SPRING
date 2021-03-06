package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.exception.AuthenticationError;
import com.brazhnyk.epam_finalproject_spring.service.IEditionService;
import com.brazhnyk.epam_finalproject_spring.service.IGenreService;
import com.brazhnyk.epam_finalproject_spring.service.IUserEditionService;
import com.brazhnyk.epam_finalproject_spring.service.IUserService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.GenreService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserEditionService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserService;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final IUserService userService;
    private final IEditionService editionService;
    private final IGenreService genreService;
    private final IUserEditionService userEditionService;

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

    @PreAuthorize("hasAnyAuthority('ROLE_BLOCKED', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/wallet")
    public String openWalletPage(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        model.addAttribute("current_balance", userFromDb.getBalance());

        return "wallet/wallet";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/wallet/top_up")
    public String openWalletTopUpPage(@AuthenticationPrincipal User user, Model model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        model.addAttribute("current_balance", userFromDb.getBalance());

        return "wallet/topUpWallet";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/wallet/top_up")
    public String topUpWallet(@AuthenticationPrincipal User user, @RequestParam String money) {
        userService.topUpBalance(user, money);

        return "redirect:/user/wallet";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_BLOCKED', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/subscriptions")
    public String openSubscriptionsPage(@AuthenticationPrincipal User user,
                                        @RequestParam(name = "currentPage", required = false) String currentPage,
                                        @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                        @RequestParam(name = "genreFilter", required = false) Genre genreFilter,
                                        @RequestParam(name = "orderBy", required = false) String orderBy,
                                        Model model) throws AuthenticationError {

        Page<Edition> page = editionService.findAllOrdered(user, currentPage, recordsPerPage, genreFilter, orderBy);
        model.addAttribute("editionList", page);

        List<Genre> genreList = genreService.findAllGenres();
        model.addAttribute("genreList", genreList);

        PaginationPresetEngine.updateModelForPagination(model, page, currentPage, recordsPerPage);

        return "edition_page/userEditions";
    }

    @PreAuthorize("hasAnyAuthority('ROLE_BLOCKED', 'ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/subscriptions/unsubscribe")
    public String unsubscribe (@AuthenticationPrincipal User user,
                               @RequestParam(name = "unsubscribe_edition_id") Edition edition) {

        userEditionService.unsubscribe(user, edition);

        return "redirect:/user/subscriptions";
    }
}
