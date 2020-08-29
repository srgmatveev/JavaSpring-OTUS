package org.sergio.library.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorMongoRepositoryTest {
    private final AuthorMongoRepository authorMongoRepository;

    AuthorMongoRepositoryTest(@Qualifier("authorRepo") AuthorMongoRepository authorMongoRepository) {
        this.authorMongoRepository = authorMongoRepository;
    }

    @BeforeEach
    void setUp() {
        authorMongoRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void findByName() {
        Author author = new Author("Mark", "Twain");
        assertNull(author.getId());
        authorMongoRepository.save(author);
        author = authorMongoRepository.findByName("Mark");
        assertNotNull(author.getId());
        assertEquals(author.getName(), "Mark");
        assertEquals(author.getSurName(), "Twain");
        author = authorMongoRepository.findByName("Mar");
        assertNull(author);
    }

    @Test
    @Transactional
    void findBySurName() {
        Author author = new Author("Mark", "Twain");
        assertNull(author.getId());
        authorMongoRepository.save(author);
        author = authorMongoRepository.findBySurName("Twain");
        assertNotNull(author.getId());
        assertEquals(author.getName(), "Mark");
        assertEquals(author.getSurName(), "Twain");
        author = authorMongoRepository.findBySurName("Twai");
        assertNull(author);
    }

    @Test
    @Transactional
    void findByNameAndSurName() {
        Author author = new Author("Mark", "Twain");
        assertNull(author.getId());
        authorMongoRepository.save(author);
        author = authorMongoRepository.findByNameAndSurName("Mark", "Twain");
        assertNotNull(author.getId());
        assertEquals(author.getName(), "Mark");
        assertEquals(author.getSurName(), "Twain");
        author = authorMongoRepository.findByNameAndSurName("Mar", "Twain");
        assertNull(author);
        author = authorMongoRepository.findByNameAndSurName("Mark", "Twai");
        assertNull(author);
    }

    @Test
    void uniqSave() {
        Author author = new Author("Sergio", "Matveev");
        authorMongoRepository.uniqSave(author);
        Author author1 = new Author("Sergio", "Matveev");
        List<Author> list = authorMongoRepository.findAll(Example.of(author1));
        assertEquals(list.size(), 1);
        Author author2 = new Author("Sergio", "Matveev");
        authorMongoRepository.uniqSave(author2);
        list = authorMongoRepository.findAll(Example.of(author1));
        assertEquals(list.size(), 1);
    }
}