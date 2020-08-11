package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.PersonRepository;
import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

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
class ShellPersonServiceTest {
    private PersonService personService;
    private PersonRepository personRepository;

    public ShellPersonServiceTest(@Qualifier("shellPersonService") PersonService personService,
                                  @Qualifier("personRepo") PersonRepository personRepository
    ) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @Test
    void login() throws Exception {
        for (int i = 0; i < 2; i++) {
            personService.login("Sergio", "Matveev");
            List<Person> list = personRepository.findByNameAndSurName("Sergio", "Matveev");
            assertTrue(list != null);
            assertEquals(list.get(0).getName(), "Sergio");
            assertEquals(list.get(0).getPersonId(), 1l);
            assertEquals(list.size(), 1);
        }
    }
}