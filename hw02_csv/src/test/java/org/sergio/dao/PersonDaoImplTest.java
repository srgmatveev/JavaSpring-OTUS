package org.sergio.dao;

import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

@ContextConfiguration("classpath:org/sergio/dao/test-context.xml")
public class PersonDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private PersonDao personDao;

    @BeforeMethod
    public void setUp() {
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    public void testFindByNameAndSurname() throws PersonDaoException {
        assertEquals(Locale.getDefault().toString(), "ru_RU");
        Person person = personDao.findByNameAndSurname("Sergey", "Matveev");
        assertEquals(person.getName(), "Sergey");
    }

    @Test(expectedExceptions = PersonDaoException.class)
    public void testFindByNameAndSurnameException() throws PersonDaoException {
        assertEquals(Locale.getDefault().toString(), "ru_RU");
        Person person = personDao.findByNameAndSurname(null, "Matveev");
    }

}