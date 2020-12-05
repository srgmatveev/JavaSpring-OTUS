package org.sergio.library.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreRepoTest {
    private final GenreRepo genreRepo;

    GenreRepoTest(@Qualifier("genreRepo") GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @BeforeEach
    void setUp() {
        genreRepo.deleteAll();
    }

    @AfterEach
    void tearDown() {
        genreRepo.deleteAll();
    }

    @Test
    void findByName() {
        Genre genre1 = new Genre("fantasy");
        assertNull(genre1.getId());
        genreRepo.save(genre1);
        assertNotNull(genre1.getId());
        Genre genre_find = genreRepo.findByName("FaNtasy");
        assertNotNull(genre_find);
        assertEquals(genre1.getId(), genre_find.getId());
        assertEquals(genre_find.getName(), "fantasy");
    }

    @Test
    void uniqSave() {
        Genre genre1 = new Genre("fantasy");
        assertNull(genre1.getId());
        genreRepo.uniqSave(genre1);
        assertNotNull(genre1.getId());
        List<Genre> list = genreRepo.findAll(Example.of(genre1));
        assertEquals(list.size(), 1);
        Genre genre2 = new Genre("FanTasy");
        genre2 = genreRepo.uniqSave(genre2);
        list = genreRepo.findAll(Example.of(genre2));
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getName(), "fantasy");
    }
}