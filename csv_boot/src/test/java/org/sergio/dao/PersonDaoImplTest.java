package org.sergio.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration("classpath:org/sergio/dao/test-context.xml")
class PersonDaoImplTest {

    @Autowired
    private PersonDao personDao;

    @BeforeEach
    public void setUp() {
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    public void testFindByNameAndSurname() throws PersonDaoException {
        assertEquals(Locale.getDefault().toString(), "ru_RU");
        Person person = personDao.findByNameAndSurname("Sergey", "Matveev");
        assertEquals(person.getName(), "Sergey");
    }

    @Test
    public void testFindByNameAndSurnameException() throws PersonDaoException {
        assertEquals(Locale.getDefault().toString(), "ru_RU");
        Assertions.assertThrows(PersonDaoException.class, () -> {
            Person person = personDao.findByNameAndSurname(null, "Matveev");
        });

    }

}
