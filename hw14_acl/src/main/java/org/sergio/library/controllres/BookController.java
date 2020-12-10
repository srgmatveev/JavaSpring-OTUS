package org.sergio.library.controllres;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.dto.BookDTO;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.service.BookService;
import org.sergio.library.validators.BookDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDTOValidator bookDTOValidator;

    @GetMapping
    String getBooks(Model model) {
        return "book/books";
    }

    @GetMapping(value = "add")
    String addBook(Model model) {
        BookDTO bookDTO = new BookDTO();
        model.addAttribute("book", bookDTO);
        return "book/add_book";
    }

    @PostMapping(value = "add")
    String saveBook(@ModelAttribute("book") BookDTO bookDTO, Model model, BindingResult result) {
        log.debug("Save book " + bookDTO.getName());
        bookDTOValidator.validate(bookDTO, result);
        log.info(result.toString());

        return "book/add_book";
    }


    @GetMapping(value = "add_authors")
    String add_author(
            @RequestParam(required = false, defaultValue = "", value = "book_id") String bookId,
            Model model) {
        log.debug("book id = " + bookId);
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order orderName = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order orderSurName = new Sort.Order(Sort.Direction.ASC, "surName");
        orders.add(orderSurName);
        orders.add(orderName);
        List<Author> list = authorRepo.findAll(Sort.by(orders));
        model.addAttribute("bookId", bookId);
        model.addAttribute("authors", list);

        return "book/add_author";
    }

    @PutMapping("{bookId}/{authorId}")
    ResponseEntity<?> updateAuthor(@PathVariable String bookId,
                                   @PathVariable String authorId,
                                   Model model) {
        log.debug(String.format("Put method running for book id = %s and author with id = %s", bookId, authorId));
        Optional<Book> bookOptional = bookService.addAuthor(bookId, authorId);
        if (bookOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body("ok");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }


}
