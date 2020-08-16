package org.sergio.library.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.sergio.library.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


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
class BookCommentsRepositoryTest {
    private BookService bookService;
    private BookCommentsRepository commentsRepository;


    public BookCommentsRepositoryTest(@Qualifier("shellBookService") BookService bookService,
                                      @Qualifier("bookCommentsRepo") BookCommentsRepository commentsRepository) {
        this.bookService = bookService;
        this.commentsRepository = commentsRepository;
    }

    @Test
    @Transactional
    void findByBookBookId() {
        Book book = bookService.newBook("Война и мир");
        book = bookService.save(book);
        BookComments bookComment1 = new BookComments();
        bookComment1.setMessage("отстой");
        BookComments bookComment2 = new BookComments();
        bookComment2.setMessage("полный");
        bookService.addComment(book, bookComment1);
        bookService.addComment(book, bookComment2);
    }

}