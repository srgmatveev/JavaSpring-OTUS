package org.sergio.library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sergio.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepoTest {
    @Autowired
    private BookRepo repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    @Test
    void findByName() {
        Book book = new Book("Steel");
        repo.save(book);
        Book book1 = repo.findByName("Steel");
        assertNotNull(book1);
        assertNotNull(book1.getId());
        assertEquals(book1.getId(), book.getId());
        assertEquals(book1.getName(), book.getName());
    }

    @Test
    void findByNameNull() {
        Book book = repo.findByName("Steel");
        assertNull(book);

    }

}