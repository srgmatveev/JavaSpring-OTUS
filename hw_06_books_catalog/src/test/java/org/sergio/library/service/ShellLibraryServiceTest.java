package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.domain.GenreRef;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ShellLibraryServiceTest {

    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    private LibraryService libraryService;

    public ShellLibraryServiceTest(@Qualifier("shellLibService") LibraryService libraryService,
                                   @Qualifier("genreRepo") GenreRepository genreRepository,
                                   @Qualifier("authorRepo") AuthorRepository authorRepository
    ) {
        this.libraryService = libraryService;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void getBooksByGenre() {
        List<Book> books = libraryService.getBooksByGenre("приключения");
        assertTrue(books.size() > 0);
        for (int i = 0; i < books.size(); i++) {
            Set<GenreRef> genreRefs = books.get(i).getGenres();
            boolean found = false;
            for (GenreRef genreRef : genreRefs) {
                if (genreRef.getGenreId().equals(3L)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void getBooksForAllGenres() {
        Map<Genre, List<Book>> map = libraryService.getBooksForAllGenres();
        assertEquals(map.size(), 4);
        List<Genre> genres = genreRepository.findByGenreName("приключения");
        Genre genre = genres.get(0);
        assertTrue(map.containsKey(genre));

        genres = genreRepository.findByGenreName("образование");
        genre = genres.get(0);
        assertTrue(map.containsKey(genre));
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void getBooksByAuthor() {
    }

    @Test
    @Sql(scripts = {"/schema.sql", "/data-test.sql"},
            config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
    void getBooksForAllAuthors() {
        Map<Author, List<Book>> map = libraryService.getBooksForAllAuthors();
        assertEquals(map.size(), 6);
    }
}