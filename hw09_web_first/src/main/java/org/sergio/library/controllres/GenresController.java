package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.validators.GenreDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class GenresController {
    @Autowired
    private GenreDTOValidator genreDTOValidator;

    @GetMapping("/genres")
    public String getGenres() {
        return "genres";
    }

    @GetMapping("/genres/add")
    public String addGenre(Model model) {
        model.addAttribute("genre", new GenreDTO());
        return "add_genre";
    }

    @PostMapping("/genres/add")
    public String addGenrePost(@ModelAttribute("genre") GenreDTO genreDTO, Model model,  BindingResult result){
        log.debug("Registering genreDTO : "+ genreDTO);
        genreDTOValidator.validate(genreDTO, result);

        log.info(result.toString());
        //model.addAttribute("genre", new GenreDTO());
        return "add_genre";
    }
}
