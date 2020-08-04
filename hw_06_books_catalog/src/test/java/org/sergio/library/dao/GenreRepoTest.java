package org.sergio.library.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.domain.GenreRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GenreRepoTest {

    private AuthorTestRepository authorRepository;
    private BookTestRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public GenreRepoTest(AuthorTestRepository authorRepository,
                         BookTestRepository bookRepository,
                         @Qualifier("genreRepo") GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void getBooksById() {
        Long id = 3l;
        Optional<Genre> genre = genreRepository.findById(id);
        assertTrue(genre.isPresent());
        List<Book> books = genreRepository.findBooksbyId(id);
        assertTrue(books.size() > 0);
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))

    void name() {
        long count = genreRepository.count();
        Genre genre = new Genre();
        genre.setGenreName("приключения");
        genreRepository.save(genre);
        assertEquals(genreRepository.count(),count);
        genre = new Genre();
        genre.setGenreName("маринистика");
        genreRepository.save(genre);
        assertEquals(genreRepository.count(),++count);
    }
}