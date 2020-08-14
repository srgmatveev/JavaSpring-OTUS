package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

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
class ShellGenreServiceTest {
    private GenreService genreService;
    private GenreRepository genreRepository;

    public ShellGenreServiceTest(@Qualifier("shellGenreService") GenreService genreService,
                                 @Qualifier("genreRepo") GenreRepository genreRepository) {
        this.genreService = genreService;
        this.genreRepository = genreRepository;
    }

    @Test
    void addGenre() {
        Genre genre = genreService.addGenre("приключения");
        Genre genreTest = genreRepository.findById(genre.getGenreId()).get();
        assertEquals(genreTest.getGenreId(), 1l);
        genre = genreService.addGenre("приключения");
        genreTest = genreRepository.findById(genre.getGenreId()).get();
        assertEquals(genreTest.getGenreId(), 1l);
    }
}