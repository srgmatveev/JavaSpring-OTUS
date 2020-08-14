package org.sergio.library.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorTestRepository;
import org.sergio.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.HashSet;

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
class AuthorTest {


    private AuthorTestRepository authorRepository;
    private AuthorService authorService;
    public AuthorTest(@Qualifier("authorTestRepo") AuthorTestRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void getAuthorId() {
        Author author = new Author();
        authorRepository.save(author);
        assertEquals(author.getAuthorId(), 1l);
    }

    @Test
    void getAuthorName() {
        Author author = new Author();
        author.setAuthorName("Sergio");
        authorRepository.save(author);
        assertEquals(author.getAuthorName(), "Sergio");
    }

    @Test
    void getAuthorSurName() {
        Author author = new Author();
        author.setAuthorSurName("Matveev");
        authorRepository.save(author);
        assertEquals(author.getAuthorSurName(), "Matveev");
    }

    @Test
    void getBooks() {
        Set<Book> bookSet = new HashSet<>();
        Book book1 = new Book();
        book1.setBookName("eeee");
        Book book2 = new Book();
        book2.setBookName("hhhh");
        bookSet.add(book1);
        bookSet.add(book2);
        Author author = new Author();
        author.setBooks(bookSet);
        assertEquals(author.getBooks().size(), 2);

    }

    @Test
    void setAuthorName() {
        Author author = new Author();
        author.setAuthorName("Sergio");
        authorRepository.save(author);
        assertEquals(author.getAuthorName(), "Sergio");

    }

    @Test
    void setAuthorSurName() {
        Author author = new Author();
        author.setAuthorSurName("Matveev");
        authorRepository.save(author);
        assertEquals(author.getAuthorSurName(), "Matveev");
    }

    @Test
    void setBooks() {
        Set<Book> bookSet = new HashSet<>();
        Book book1 = new Book();
        book1.setBookName("eeee");
        Book book2 = new Book();
        book2.setBookName("hhhh");
        bookSet.add(book1);
        bookSet.add(book2);
        Author author = new Author();
        author.setBooks(bookSet);
        assertEquals(author.getBooks().size(), 2);
    }
}