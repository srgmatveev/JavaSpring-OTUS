package org.sergio.library.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.GenreTestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema_test.sql"},
                config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:drop_test.sql",
                config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED)
        )
})
class GenreTest {
    private final GenreTestRepository genreRepository;

    GenreTest(@Qualifier("genreTestRepo") GenreTestRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Test
    void getGenreId() {
        Genre genre = new Genre();
        genre.setGenreName("adventure");
        genreRepository.save(genre);
        assertEquals(genre.getGenreId(), 1l);
    }

    @Test
    void getGenreName() {
        Genre genre = new Genre();
        genre.setGenreName("adventure");
        genreRepository.save(genre);
        assertEquals(genre.getGenreName(), "adventure");
    }

    @Test
    void getBooks() {
        Genre genre = new Genre();
        Set<Book> books = new HashSet<>();
        Book book1 = new Book();
        book1.setBookName("Azbuka");
        Book book2 = new Book();
        book2.setBookName("Hunters");
        books.add(book1);
        books.add(book2);
        genre.setBooks(books);
        assertEquals(genre.getBooks().size(), 2);
    }

    @Test
    void setGenreName() {
        Genre genre = new Genre();
        genre.setGenreName("adventure");
        genreRepository.save(genre);
        assertEquals(genre.getGenreName(), "adventure");
    }

    @Test
    void setBooks() {
        Genre genre = new Genre();
        Set<Book> books = new HashSet<>();
        Book book1 = new Book();
        book1.setBookName("Azbuka");
        Book book2 = new Book();
        book2.setBookName("Hunters");
        books.add(book1);
        books.add(book2);
        genre.setBooks(books);
        assertEquals(genre.getBooks().size(), 2);
    }
}