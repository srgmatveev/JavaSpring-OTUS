package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Author;
import org.sergio.library.dto.BookDTO;
import org.sergio.library.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private AuthorRepo authorRepo;

    @GetMapping
    String getBooks(Model model) {
        return "book/books";
    }

    @GetMapping(value = "add")
    String addBook(Model model) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId("1111");
        model.addAttribute("book", bookDTO);
        return "book/add_book";
    }

    @GetMapping(value = "add_authors")
    String add_author(Model model) {
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order orderName = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order orderSurName = new Sort.Order(Sort.Direction.ASC, "surName");
        orders.add(orderSurName);
        orders.add(orderName);
        List<Author> list = authorRepo.findAll(Sort.by(orders));
        model.addAttribute("authors", list);

        return "book/add_author";
    }

}
