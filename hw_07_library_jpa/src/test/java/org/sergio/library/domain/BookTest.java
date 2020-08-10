package org.sergio.library.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.BookTestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

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
class BookTest {
    private final BookTestRepository bookRepository;

    BookTest(@Qualifier("bookTestRepo") BookTestRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void getBookId() {
        Book book = new Book();
        book.setBookName("Zhivago");
        bookRepository.save(book);
        assertEquals(book.getBookId(), 1l);
    }

    @Test
    void getBookName() {
        Book book = new Book();
        book.setBookName("Zhivago");
        bookRepository.save(book);
        assertEquals(book.getBookName(), "Zhivago");
    }

    @Test
    void getGenres() {
        Set<Genre> genres = new HashSet<>();
        Genre genre1 = new Genre();
        genre1.setGenreName("fantasy");
        Genre genre2 = new Genre();
        genre2.setGenreName("adventure");
        genres.add(genre1);
        genres.add(genre2);
        Book book = new Book();
        book.setGenres(genres);
        assertEquals(book.getGenres().size(), 2);

    }

    @Test
    void getAuthors() {
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author();
        author1.setAuthorName("Boy");
        Author author2 = new Author();
        author2.setAuthorName("Artur");
        authors.add(author1);
        authors.add(author2);
        Book book = new Book();
        book.setAuthors(authors);
        assertEquals(book.getAuthors().size(), 2);
    }

    @Test
    void getComments() {
        Set<BookComments> comments = new HashSet<>();
        BookComments comments1 = new BookComments();
        comments1.setMessage("hello");
        BookComments comments2 = new BookComments();
        comments2.setMessage("world");
        comments.add(comments1);
        comments.add(comments2);
        Book book = new Book();
        book.setComments(comments);
        assertEquals(book.getComments().size(), 2);
    }

    @Test
    void setBookName() {
        Book book = new Book();
        book.setBookName("Zhivago");
        bookRepository.save(book);
        assertEquals(book.getBookName(), "Zhivago");
    }

    @Test
    void setGenres() {
        Set<Genre> genres = new HashSet<>();
        Genre genre1 = new Genre();
        genre1.setGenreName("fantasy");
        Genre genre2 = new Genre();
        genre2.setGenreName("adventure");
        genres.add(genre1);
        genres.add(genre2);
        Book book = new Book();
        book.setGenres(genres);
        assertEquals(book.getGenres().size(), 2);
    }

    @Test
    void setAuthors() {
        Set<Author> authors = new HashSet<>();
        Author author1 = new Author();
        author1.setAuthorName("Boy");
        Author author2 = new Author();
        author2.setAuthorName("Artur");
        authors.add(author1);
        authors.add(author2);
        Book book = new Book();
        book.setAuthors(authors);
        assertEquals(book.getAuthors().size(), 2);
    }
}