package org.sergio.library.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorRepoTest {
    private AuthorRepository authorRepository;

    private BookTestRepository bookRepository;
    private GenreTestRepository genreRepository;

       @Autowired
    public AuthorRepoTest(@Qualifier("authorRepo") AuthorRepository authorRepository,
                          BookTestRepository bookRepository, GenreTestRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }


    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void findByAuthorNameStartingWith() {
        List<Author> authors = authorRepository.findByAuthorNameStartingWith("Ма");
        assertEquals(authors.size(), 2);
        for (int i = 0; i < authors.size(); i++) {
            assertEquals(authors.get(i).getAuthorName(), "Марк");
        }
    }


    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void findByAuthorNameStartingWithAndAuthorSurNameStartingWith() {
        List<Author> authors = authorRepository.findByAuthorNameStartingWithAndAuthorSurNameStartingWith("Ма", "Т");
        assertEquals(authors.size(), 1);
        for (int i = 0; i < authors.size(); i++) {
            assertEquals(authors.get(i).getAuthorName(), "Марк");
            assertEquals(authors.get(i).getAuthorSurName(), "Твен");
        }
    }


}