package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Author;
import org.sergio.library.dto.AuthorDTO;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.validators.AuthorDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class AuthorsController {
    @Autowired
    AuthorDTOValidator validator;

    @Autowired
    AuthorRepo repo;

    @GetMapping("/authors")
    String getAuthors(Model model) {
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order orderName = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order orderSurName = new Sort.Order(Sort.Direction.ASC, "surName");
        orders.add(orderSurName);
        orders.add(orderName);
        List<Author> list = repo.findAll(Sort.by(orders));
        model.addAttribute("authors", list);
        return "authors";
    }

    @GetMapping("/authors/add")
    String addAuthor(Model model) {
        model.addAttribute("author", new AuthorDTO());
        return "add_author";
    }

    @PostMapping("/authors/add")
    String addAuthorPost(@ModelAttribute("author") AuthorDTO authorDTO, Model model, BindingResult result) {
        validator.validate(authorDTO, result);
        return "add_author";
    }

    @DeleteMapping("/authors/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    void delAuthor(@PathVariable("id") String id) {
        repo.deleteById(id);
    }

    @GetMapping("/authors/edit")
    String editAuthor(@RequestParam("id") String id,
                      @RequestParam("name") String name,
                      @RequestParam("surname") String surName,
                      Model model) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(id);
        authorDTO.setName(name);
        authorDTO.setSurName(surName);
        model.addAttribute("author", authorDTO);
        return "edit_author";
    }
    @PostMapping("/authors/edit")
    String editGenrePost(@ModelAttribute("author") AuthorDTO authorDTO, Model model, BindingResult result) {
        log.debug("Registering authorDTO : " + authorDTO);
        validator.validate(authorDTO, result);

        log.info(result.toString());
        //model.addAttribute("genre", new GenreDTO());

        if(result.hasErrors())
            return "edit_author";
        else return "redirect:/authors";
    }
}
