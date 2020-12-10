package org.sergio.library.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepo.deleteAll();
        authorRepo.deleteAll();
    }

    @Test
    void addAuthor() {
        Author author1 = new Author("Ivan","Krilov");
        authorRepo.save(author1);
        assertNotNull(author1.getId());
        Book book = new Book("wwww");
        bookRepo.save(book);
        assertNotNull(book.getId());
        assertEquals(book.getAuthors_ids().size(),0);
        for (int i = 0; i < 2; i++) {
            Optional<Book> optionalBook = bookService.addAuthor(book.getId(), author1.getId());
            List<String> authors_ids = optionalBook.get().getAuthors_ids();
            assertEquals(authors_ids.size(), 1);
            assertEquals(authors_ids.get(0), author1.getId());
        }

        Author author2 = new Author("Vovan","Divan");
        authorRepo.save(author2);
        assertNotNull(author2.getId());

        Optional<Book> optionalBook = bookService.addAuthor(book.getId(), author2.getId());
        List<String> authors_ids = optionalBook.get().getAuthors_ids();
        assertEquals(authors_ids.size(), 2);
        assertEquals(authors_ids.get(0), author1.getId());
        assertEquals(authors_ids.get(1), author2.getId());

    }
}