package org.sergio.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.BookCommentsTestRepository;
import org.sergio.library.dao.PersonTestRepository;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class ShellLibraryServiceTest {
    private final LibraryService ls;
    private final BookService bookService;
    private final BookCommentsTestRepository commentsRepository;
    private final PersonTestRepository personRepository;

    ShellLibraryServiceTest(@Qualifier("shellLibService") LibraryService ls,
                            @Qualifier("shellBookService") BookService bookService,
                            @Qualifier("commentsTestRepo") BookCommentsTestRepository commentsRepository,
                            @Qualifier("personTestRepo") PersonTestRepository personRepository) {
        this.ls = ls;
        this.bookService = bookService;
        this.commentsRepository = commentsRepository;
        this.personRepository = personRepository;
    }


    @Test
    void showComments() {
        Person person = new Person();
        person.setName("Sergio");
        person.setSurName("Matveev");
        personRepository.save(person);
        Book book = bookService.newBook("Война и мир");
        Set<BookComments> bookComments = new HashSet<>();
        BookComments bookComment1 = new BookComments();
        bookComment1.setMessage("отстой");
        bookComment1.setPerson(person);
        BookComments bookComment2 = new BookComments();
        bookComment2.setMessage("полный");
        bookComments.add(bookComment1);
        bookComments.add(bookComment2);
        bookService.addComments(book, bookComments);
        assertEquals(book.getComments().size(), 2);

        String result = ls.showComments("Война и мир");
        assertTrue(result.startsWith("Война и мир:"));
        //System.out.println(result);
    }
}