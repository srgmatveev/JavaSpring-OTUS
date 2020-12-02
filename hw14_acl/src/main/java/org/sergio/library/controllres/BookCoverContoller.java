package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.dto.Cover;
import org.sergio.library.exceptions.UniqueFileUploadException;
import org.sergio.library.repository.GridFSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j
public class BookCoverContoller {
    @Autowired
    @Qualifier("gridFSRepo")
    private GridFSRepo gridFSRepo;


    @GetMapping("/covers")
    String getBookCovers(Model model) {
        List<Cover> covers = gridFSRepo.findAll();
        model.addAttribute("covers", covers);
        return "cover/book_covers";
    }

    @GetMapping("/covers/add")
    String addBookCoversGet(Model model) {
        return "cover/add_cover";
    }

    @PostMapping("/covers/add")
    @ResponseBody
    ResponseEntity addBookCovers(@RequestParam("file") MultipartFile file, Model model) {
        log.debug(file.getOriginalFilename());
        log.debug(String.valueOf(file.getSize()));
        if (isImage(file.getContentType())) {
            try {
                gridFSRepo.uniqFileUpload(file);
            } catch (UniqueFileUploadException e) {
                log.debug(e.toString());
            }
            return new ResponseEntity(HttpStatus.OK);
        } else {
            log.debug(String.format("%s is not image. Reject", file.getOriginalFilename()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping(value = "/covers/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        gridFSRepo.delete(id);
    }

    private boolean isImage(String str) {
        if (str.startsWith("image/"))
            return true;
        return false;
    }
}
