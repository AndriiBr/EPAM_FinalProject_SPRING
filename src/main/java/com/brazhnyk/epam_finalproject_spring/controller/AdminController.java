package com.brazhnyk.epam_finalproject_spring.controller;

import com.brazhnyk.epam_finalproject_spring.entity.Edition;
import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import com.brazhnyk.epam_finalproject_spring.entity.User;
import com.brazhnyk.epam_finalproject_spring.service.IEditionService;
import com.brazhnyk.epam_finalproject_spring.service.IGenreService;
import com.brazhnyk.epam_finalproject_spring.service.IUserService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.EditionService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.GenreService;
import com.brazhnyk.epam_finalproject_spring.service.implementation.UserService;
import com.brazhnyk.epam_finalproject_spring.util.InputValidator;
import com.brazhnyk.epam_finalproject_spring.util.PaginationPresetEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${static.content}")
    private String staticContent;

    private static final String REDIRECT_TO_ADMIN_EDITIONS = "redirect:/admin/edition";

    private final IUserService userService;
    private final IEditionService editionService;
    private final IGenreService genreService;

    @Autowired
    public AdminController(UserService userService, EditionService editionService, GenreService genreService) {
        this.userService = userService;
        this.editionService = editionService;
        this.genreService = genreService;
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user-list")
    public String openUserListPage(@RequestParam(name = "currentPage", required = false) String currentPage,
                                   @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                   Model model) {
        Page<User> page = userService.getUsersPage(currentPage, recordsPerPage);
        model.addAttribute("userList", page);

        PaginationPresetEngine.updateModelForPagination(model, page, currentPage, recordsPerPage);

        return "admin/users";
    }


    @GetMapping("/edition")
    public String openEditionListPage(@RequestParam(name = "currentPage", required = false) String currentPage,
                                      @RequestParam(name = "recordsPerPage", required = false) String recordsPerPage,
                                      @RequestParam(name = "genreFilter", required = false) Genre genreFilter,
                                      @RequestParam(name = "orderBy", required = false) String orderBy,
                                      Model model) {
        Page<Edition> page = editionService.findAll(currentPage, recordsPerPage, genreFilter, orderBy);
        model.addAttribute("editionList", page);

        List<Genre> genreList = genreService.findAllGenres();
        model.addAttribute("genreList", genreList);

        PaginationPresetEngine.updateModelForPagination(model, page, currentPage, recordsPerPage);

        return "admin/adminEditions";
    }

    @GetMapping("/edition/new-edition")
    public String openNewEditionPage(Model model) {

        List<Genre> genreList = genreService.findAllGenres();
        model.addAttribute("genreList", genreList);

        return "edition_page/addEditionForm";
    }

    @PostMapping("/edition/new-edition")
    public String addNewEdition(@RequestParam("title_en") String titleEn,
                                @RequestParam("title_ua") String titleUa,
                                @RequestParam("text_en") String textEn,
                                @RequestParam("text_ua") String textUa,
                                @RequestParam("price") String price,
                                @RequestParam("genre") Genre genre,
                                @RequestParam("file-name")MultipartFile file) throws IOException {
        Edition edition = null;

        if (InputValidator.validateNewEdition(titleEn, titleUa, textEn, textUa, price, genre)) {
            edition = new Edition(titleEn, titleUa, textEn, textUa, Integer.parseInt(price), genre);
        }

        if (edition != null) {
            saveImage(file, edition);

            editionService.saveEdition(edition);
        }

        return REDIRECT_TO_ADMIN_EDITIONS;
    }

    @PostMapping("edition/delete")
    public String deleteEdition(@RequestParam("delete_edition_id") Edition edition) {

        editionService.deleteEdition(edition);

        return REDIRECT_TO_ADMIN_EDITIONS;
    }

    @GetMapping("/edition/edit")
    public String openEditionEditPage(@RequestParam("edit_edition_id") Edition edition, Model model) {

        List<Genre> genreList = genreService.findAllGenres();

        model.addAttribute("editEdition", edition);
        model.addAttribute("genreList", genreList);

        return "edition_page/editEditionForm";
    }

    @PostMapping("/edition/edit")
    public String updateEdition(@RequestParam("edit_edition_id") Edition edition,
                                @RequestParam("title_en") String titleEn,
                                @RequestParam("title_ua") String titleUa,
                                @RequestParam("text_en") String textEn,
                                @RequestParam("text_ua") String textUa,
                                @RequestParam("price") String price,
                                @RequestParam(name = "genre", required = false) Genre genre,
                                @RequestParam("file-name")MultipartFile file) throws IOException {

        if (edition != null) {
            boolean result = updateEdition(edition, titleEn, titleUa, textEn, textUa, price, genre);
            if (result) {
                saveImage(file, edition);

                editionService.saveEdition(edition);
            }
        }

        return REDIRECT_TO_ADMIN_EDITIONS;
    }

    @PostMapping("/user-list/block")
    public String userBlockUnblock(@RequestParam("user_block") User user) {

        userService.updateUserRole(user);

        return "redirect:/admin/user-list";
    }

    /**
     * Update edition entity
     * @param edition - edition entity
     * @param titleEn - title En.
     * @param titleUa - title Ua.
     * @param textEn - text En.
     * @param textUa - text Ua
     * @param price - price
     * @param genre - genre
     * @return TRUE - if validation and update were success.
     */
    private boolean updateEdition(Edition edition,
                               String titleEn,
                               String titleUa,
                               String textEn,
                               String textUa, String price, Genre genre) {
        if (InputValidator.validateNewEdition(titleEn, titleUa, textEn, textUa, price, genre)) {
            edition.setTitleEn(titleEn);
            edition.setTitleUa(titleUa);
            edition.setTextEn(textEn);
            edition.setTextUa(textUa);
            edition.setPrice(Integer.parseInt(price));
            if (genre != null) {
                edition.setGenre(genre);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Save image to external folder and put path into edition entity field 'imagePath'
     * @param file - image
     * @param edition - edition to be updated
     * @throws IOException when cannot save file
     */
    public void saveImage(MultipartFile file, Edition edition) throws IOException {
        if(file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            edition.setTitleImage(staticContent + resultFileName);
        }
    }
}
