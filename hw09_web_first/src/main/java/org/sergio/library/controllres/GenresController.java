package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.GenreRepo;
import org.sergio.library.validators.GenreDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class GenresController {
    @Autowired
    private GenreDTOValidator genreDTOValidator;

    @Autowired
    GenreRepo genreRepo;

    @GetMapping("/genres")
    public String getGenres(Model model) {
        List<Genre> genres = genreRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("genres", genres);
        return "genre/genres";
    }

    @GetMapping("/genres/add")
    public String addGenre(Model model) {
        model.addAttribute("genre", new GenreDTO());
        return "genre/add_genre";
    }

    @PostMapping("/genres/add")
    public String addGenrePost(@ModelAttribute("genre") GenreDTO genreDTO, Model model, BindingResult result) {
        log.debug("Registering genreDTO : " + genreDTO);
        genreDTOValidator.validate(genreDTO, result);

        log.info(result.toString());
        //model.addAttribute("genre", new GenreDTO());
        return "genre/add_genre";
    }

    @DeleteMapping(value = "/genres/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        genreRepo.deleteById(id);
    }

    @GetMapping("/genres/edit")
    String editGenre(@RequestParam("id") String id,
                     @RequestParam("name") String name,
                     Model model) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(id);
        genreDTO.setName(name);
        model.addAttribute("genre", genreDTO);
        return "genre/edit_genre";
    }

    @PostMapping("/genres/edit")
    String editGenrePost(@ModelAttribute("genre") GenreDTO genreDTO, Model model, BindingResult result) {
        log.debug("Registering genreDTO : " + genreDTO);
        genreDTOValidator.validate(genreDTO, result);

        log.info(result.toString());
        //model.addAttribute("genre", new GenreDTO());

        if (result.hasErrors())
            return "genre/edit_genre";
        else return "redirect:/genres";
    }

}
