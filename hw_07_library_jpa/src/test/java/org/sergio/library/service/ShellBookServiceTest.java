package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.BookCommentsTestRepository;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

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
class ShellBookServiceTest {
    private BookService bookService;

    public ShellBookServiceTest(@Qualifier("shellBookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    void getBooksForAllGenres() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBooksForAllAuthors() {
    }

    @Test
    @Transactional
    void newBook() {
        Book book = bookService.newBook("Война и мир");
        book = bookService.save(book);
        assertEquals(book.getBookName(), "Война и мир");
    }

    @Test
    @Transactional
    void addCommnets() {
        Book book = bookService.newBook("Война и мир");
        Set<BookComments> bookComments = new HashSet<>();
        BookComments bookComment1 = new BookComments();
        bookComment1.setMessage("отстой");
        BookComments bookComment2 = new BookComments();
        bookComment2.setMessage("полный");
        bookComments.add(bookComment1);
        bookComments.add(bookComment2);
        bookService.addComments(book, bookComments);

        assertEquals(book.getComments().size(), 2);
        Set<BookComments> bookCommentsNew = new HashSet<>();
        BookComments bookComment3 = new BookComments();
        bookComment3.setMessage("ужасно отстой");
        BookComments bookComment4 = new BookComments();
        bookComment4.setMessage("ужасно полный");
        bookCommentsNew.add(bookComment3);
        bookCommentsNew.add(bookComment4);
        bookService.addComments(book, bookCommentsNew);
        assertEquals(book.getComments().size(), 4);

    }

    @Test
    void save() {
    }
}