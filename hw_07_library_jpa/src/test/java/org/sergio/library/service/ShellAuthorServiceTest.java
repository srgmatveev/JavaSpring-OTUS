package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.AuthorTestRepository;
import org.sergio.library.domain.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
class ShellAuthorServiceTest {
    private AuthorService authorService;
    private AuthorRepository authorRepository;

    public ShellAuthorServiceTest(@Qualifier("shellAuthorService") AuthorService authorService,
                                  @Qualifier("authorRepo") AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @Test
    void addAuthor() {
        Author author = authorService.addAuthor("Sergio", "Matveev");
        Author authorTest = authorRepository.findById(author.getAuthorId()).get();
        assertEquals(authorTest.getAuthorId(), 1l);
        author = authorService.addAuthor("Sergio", "Matveev");
        authorTest = authorRepository.findById(author.getAuthorId()).get();
        assertEquals(authorTest.getAuthorId(), 1l);
    }
}