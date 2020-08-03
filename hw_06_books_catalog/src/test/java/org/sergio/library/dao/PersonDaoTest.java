package org.sergio.library.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonDaoTest {

    private List<Person> personList;

    private PersonDao personDao;

    public PersonDaoTest(@Qualifier("customPersonDao") PersonDao personDao) {
        this.personDao = personDao;
        providerPersonsData().forEach(x -> {
            personDao.save(x);
        });
    }

    @BeforeEach
    void setUp() {

    }

    private static List<Person> providerPersonsData() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Sergey", "Matveev"));
        list.add(new Person("Misha", "Petrov"));
        return list;
    }


    @Test
    void name() {
        Person person = personDao.findByNameAndSurName("Sergey", "Matveev").get(0);
        assertEquals(person.getName(), "Sergey");
        assertEquals(person.getSurName(), "Matveev");
    }

    @Test
    void save_unique() {
        long count = personDao.count();
        personDao.save(new Person("Sergey", "Esenin"));
        personDao.save(new Person("Sergey", "Esenin"));
        assertEquals(++count, personDao.count());
    }
}