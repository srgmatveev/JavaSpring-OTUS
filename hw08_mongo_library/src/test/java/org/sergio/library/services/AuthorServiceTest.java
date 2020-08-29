package org.sergio.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorServiceTest {
    private final AuthorService authorService;
    private final AuthorMongoRepository repository;


    AuthorServiceTest(@Qualifier("authorService") AuthorService authorService, @Qualifier("authorRepo") AuthorMongoRepository repository) {
        this.authorService = authorService;
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    void addAuthor() {
        Author author = authorService.addAuthor("Mark", "Twain");
        assertEquals(author.getName(), "Mark");
        assertNotNull(author.getId());
    }
}