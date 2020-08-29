package org.sergio.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Genre;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GenreServiceTest {
    private final GenreService genreService;
    private final GenreMongoRepository repository;

    GenreServiceTest(@Qualifier("genreService") GenreService genreService, @Qualifier("genreRepo") GenreMongoRepository repository) {
        this.genreService = genreService;
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    void addGenre() {
        Genre genre = genreService.addGenre("adventures");
        assertNotNull(genre.getId());
        assertEquals(genre.getName(),"adventures");
    }
}