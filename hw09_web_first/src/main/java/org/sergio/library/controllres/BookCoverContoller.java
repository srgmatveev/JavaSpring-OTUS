package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.sergio.library.repository.GridFSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class BookCoverContoller {
    @Autowired
    @Qualifier("gridFSRepo")
    private GridFSRepo gridFSRepo;


    @GetMapping("/covers")
    String getBookCovers(Model model) {
        return "book_covers";
    }

    @GetMapping("/covers/add")
    String addBookCoversGet(Model model) {
        return "add_cover";
    }

    @PostMapping("/covers/add")
    String addBookCovers(@RequestParam("file") MultipartFile file, Model model) {
        log.debug(file.getOriginalFilename());
        log.debug(String.valueOf(file.getSize()));
        log.debug("hello");
        try {
            gridFSRepo.uniqFileUpload(file);
        } catch (UniqueFileUploadException e) {
            log.debug(e.toString());
        }
        return "add_cover";
    }
}
