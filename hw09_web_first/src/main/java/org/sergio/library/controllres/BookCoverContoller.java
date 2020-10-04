package org.sergio.library.controllres;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookCoverContoller {

    @GetMapping("/covers")
    String getBookCovers(Model model) {
        return "book_covers";
    }
}
