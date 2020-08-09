package org.sergio.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Equals;
import org.sergio.library.dao.PersonTestRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
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
class PersonTest {


    private PersonTestRepository personRepository;

    public PersonTest(@Qualifier("personTestRepo") PersonTestRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    @DisplayName("get Person id Test")
    void getPersonId() {
        Person person = new Person("Sergio", "Matveev");
        assertEquals(person.getPersonId(), null);
        person = personRepository.save(person);
        assertEquals(person.getPersonId(), 1, "Person id should be equals to 1");
        person = personRepository.save(new Person("Vasya", "Gorny"));
        assertEquals(person.getPersonId(), 2, "Person id should be equals to 2");
    }

    @Test
    void getName() {
        Person person = new Person("Mario", "Gorny");
        person = personRepository.save(person);
        assertEquals(person.getName(), "Mario",  "Person name should be equals to Mario");
    }

    @Test
    void getSurName() {
        Person person = new Person("Mario", "Gorny");
        person = personRepository.save(person);
        assertEquals(person.getSurName(), "Gorny", "Person SurName should be equals to Gorny");
    }

    @Test
    void setName() {
        Person person = new Person("Mario", "Gorny");
        person.setName("Sergio");
        person = personRepository.save(person);
        assertEquals(person.getName(),"Sergio", "Person name should be equals to Sergio");
    }

    @Test
    void setSurName() {
        Person person = new Person("Mario", "Gorny");
        person.setSurName("Matveev");
        person = personRepository.save(person);
        assertEquals(person.getSurName(), "Matveev", "Person SurName should be equals to Matveev");
    }

}
