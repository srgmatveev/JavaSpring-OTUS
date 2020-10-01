package org.sergio.library.controllres;

import org.sergio.library.domain.Genre;
import org.sergio.library.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    GenreRepo genreRepo;

    @GetMapping("/")
    String index(Model model){
        List<Genre> genreList = genreRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("genres", genreList);
        return "index";
    }
}
