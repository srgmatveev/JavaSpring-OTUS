package org.sergio.library.service;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.dao.PersonDao;
import org.sergio.library.domain.Person;
import org.sergio.library.exceptions.PersonDaoException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service(value = "shellPersonService")
public class ShellPersonService implements PersonService {
    private PersonDao personDao;
    private MessageSource ms;

    public ShellPersonService(@Qualifier("customPersonDao") PersonDao personDao, MessageSource ms) {
        this.personDao = personDao;
        this.ms = ms;
    }

    @Override
    public void login(String name, String surName) throws Exception {
        Person person;
        if (personDao != null) {
            List<Person> list = this.personDao.findByNameAndSurName(name, surName);
            if (list != null && !list.isEmpty()) {
                person = list.get(0);
            } else {
                person = new Person(name, surName);
                personDao.save(person);
            }
        }

    }
}
