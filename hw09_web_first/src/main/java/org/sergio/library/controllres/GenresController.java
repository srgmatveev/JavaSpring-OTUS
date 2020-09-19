package org.sergio.library.controllres;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {
    @GetMapping("/genres")
    String getGenres() {
        return "genres";
    }

    @GetMapping("/genres/add")
    String addGenre() {
        return "add_genre";
    }
}
