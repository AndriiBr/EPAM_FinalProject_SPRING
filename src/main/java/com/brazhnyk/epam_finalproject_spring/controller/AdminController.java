package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.service.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.GenreService;
import com.brazhnyk.epam_finalproject_spring.service.UserService;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final EditionService editionService;
    private final GenreService genreService;

    @Autowired
    public AdminController(UserService userService, EditionService editionService, GenreService genreService) {
        this.userService = userService;
        this.editionService = editionService;
        this.genreService = genreService;
    }

    @GetMapping("/user-list")
    public String openUserListPage(@RequestParam(name = "currentPage", required = false) String currentPage,
                                   @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                   Model model) {
        Page<User> userList = userService.getUsersPage(currentPage, recordsPerPage);

        model.addAttribute("userList", userList);

        model.addAttribute("pageNumbers", PaginationPresetCreator.preparePageNumbers(userList));
        model.addAttribute("itemStep", PaginationPresetCreator.prepareItemStep(3, 5, 7, 10));
        model.addAttribute("currentPage", currentPage != null ? currentPage : 1);
        model.addAttribute("recordsPerPage", recordsPerPage != null ? recordsPerPage : 5);
        model.addAttribute("totalPages", PaginationPresetCreator.preparePageNumbers(userList).size());

        return "admin/users";
    }

    @GetMapping("/edition")
    public String openEditionListPage(@RequestParam(name = "currentPage", required = false) String currentPage,
                                      @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                      Model model) {
        Page<Edition> editionList = editionService.getEditionPage(currentPage, recordsPerPage);
        List<Genre> genreList = genreService.findAllGenres();

        model.addAttribute("editionList", editionList);
        model.addAttribute("genreList", genreList);

        model.addAttribute("pageNumbers", PaginationPresetCreator.preparePageNumbers(editionList));
        model.addAttribute("itemStep", PaginationPresetCreator.prepareItemStep(3, 5, 7, 10));
        model.addAttribute("currentPage", currentPage != null ? currentPage : 1);
        model.addAttribute("recordsPerPage", recordsPerPage != null ? recordsPerPage : 5);
        model.addAttribute("totalPages", PaginationPresetCreator.preparePageNumbers(editionList).size());

        return "admin/adminEditions";
    }
}
