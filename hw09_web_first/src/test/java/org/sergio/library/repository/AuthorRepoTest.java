package org.sergio.library.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sergio.library.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorRepoTest {
    private final AuthorRepo authorRepo;

    @Autowired
    AuthorRepoTest(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @BeforeEach
    void setUp() {
        authorRepo.deleteAll();
    }

    @AfterEach
    void tearDown() {
        authorRepo.deleteAll();
    }

    @Test
    void findByName() {
        Author author = new Author("Sergio", "Matveev");
        assertNull(author.getId());
        authorRepo.save(author);
        assertNotNull(author.getId());
        List<Author> list = authorRepo.findByName("sergio");
        assertEquals(list.size(),1);
        assertEquals(list.get(0).getId(),author.getId());
    }

    @Test
    void findBySurName() {
        Author author = new Author("Sergio", "Matveev");
        assertNull(author.getId());
        authorRepo.save(author);
        assertNotNull(author.getId());
        List<Author> list = authorRepo.findBySurName("matveev");
        assertEquals(list.size(),1);
        assertEquals(list.get(0).getId(),author.getId());
        assertEquals(list.get(0).getSurName(),"Matveev");
    }

    @Test
    void findByNameAndSurName() {
        Author author = new Author("Sergio", "Matveev");
        assertNull(author.getId());
        authorRepo.save(author);
        assertNotNull(author.getId());
        Author author1 = authorRepo.findByNameAndSurName("sergio","matveev");
        assertNotNull(author1);
        assertEquals(author1.getId(),author.getId());
        assertEquals(author1.getSurName(),"Matveev");

    }

    @Test
    void uniqSave() {
        Author author1 = new Author("Sergio", "Matveev");
        assertNull(author1.getId());
        authorRepo.uniqSave(author1);
        assertNotNull(author1.getId());
        List<Author> list = authorRepo.findAll(Example.of(author1));
        assertEquals(list.size(), 1);
        Author author2 = new Author("Sergio", "Matveev");
        author2 = authorRepo.uniqSave(author2);
        list = authorRepo.findAll(Example.of(author2));
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getName(), "Sergio");
    }
}