package org.sergio.library.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.BookCommentsTestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
class BookCommentsTest {

    private final BookCommentsTestRepository commentsRepository;

    BookCommentsTest(@Qualifier("commentsTestRepo") BookCommentsTestRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Test
    void getId() {
    }

    @Test
    void getBook() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void getRegisteredAt() {
    }

    @Test
    void getMessage() {
    }

    @Test
    void setBook() {
    }

    @Test
    void setPerson() {
    }

    @Test
    void setRegisteredAt() {
    }

    @Test
    void setMessage() {
    }
}