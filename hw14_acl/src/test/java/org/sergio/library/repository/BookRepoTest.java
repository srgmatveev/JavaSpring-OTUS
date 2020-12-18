package org.sergio.library.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepoTest {
    @Autowired
    private BookRepo repo;

    @Autowired
    private GenreRepo genreRepo;

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

    @Test
    void findByGenreId() {
        Genre genre1 = new Genre("fantasy");
        genreRepo.save(genre1);
        assertNotNull(genre1.getId());

        Genre genre2 = new Genre("history");
        genreRepo.save(genre2);
        assertNotNull(genre2.getId());

        Book book = new Book("Steel");
        book.setGenres_ids(Arrays.asList(genre1.getId(), genre2.getId()));
        repo.save(book);
        assertNotNull(book.getId());

        Book book1 = new Book("Steel1");
        book1.setGenres_ids(Arrays.asList(genre1.getId()));
        repo.save(book1);

        assertNotNull(book1.getId());

        Book book2 = new Book("Steel2");
        book2.setGenres_ids(Arrays.asList(genre2.getId()));
        repo.save(book2);
        assertNotNull(book2.getId());


        List<Book> list = repo.findByGenreId(genre1.getId());
        assertNotNull(list);
        assertEquals(list.size(),2);
        assertEquals(list.get(0).getId(), book.getId());
        assertEquals(list.get(1).getId(), book1.getId());

        List<Book> list1 = repo.findByGenreId(genre2.getId());
        assertNotNull(list1);
        assertEquals(list1.size(),2);
        assertEquals(list1.get(0).getId(), book.getId());
        assertEquals(list1.get(1).getId(), book2.getId());

    }
}