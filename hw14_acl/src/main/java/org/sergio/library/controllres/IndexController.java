package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Genre;
import org.sergio.library.repository.GenreRepo;
import org.sergio.library.service.BookDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    GenreRepo genreRepo;

    @Autowired
    BookDTOService bookDTOService;

    @GetMapping("/")
    String index(Model model) {
        List<Genre> genreList = genreRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("genres", genreList);
        if (genreList != null && genreList.size() > 0) {
            model.addAttribute("genreId", genreList.get(0).getId());
            model.addAttribute("albums", bookDTOService.findAllBooksDTObyGenre(genreList.get(0).getId()));
        } else {
            model.addAttribute("genreId", "");
        }
        return "index";
    }

    @PostMapping("/")
    String postIndex(Model model, HttpServletRequest request) {
        List<Genre> genreList = genreRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("genres", genreList);
        String genreId = getId(request);
        log.debug(String.format("POST request with genreId = %s", genreId));
        if (genreId != null && !genreId.isBlank())
            model.addAttribute("genreId", genreId);
        else
            model.addAttribute("genreId", "");
        model.addAttribute("albums", bookDTOService.findAllBooksDTObyGenre(genreId));
        return "index";
    }

    private String getId(HttpServletRequest request) {
        String val = null;
        if (request != null) {
            Enumeration<String> names = request.getParameterNames();
            if (names != null && names.hasMoreElements())
                val = names.nextElement();
        }
        return val;
    }

}
