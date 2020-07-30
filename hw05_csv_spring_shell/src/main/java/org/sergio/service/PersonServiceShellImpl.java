package org.sergio.service;

import org.sergio.dao.PersonDao;
import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service(value = "shellPersonService")
public class PersonServiceShellImpl implements PersonService {
    private final PersonDao personDao;
    private Person person;
    private String name;
    private String surName;
    private final MessageSource ms;

    public PersonServiceShellImpl(PersonDao personDao, MessageSource ms) throws IOException {
        this.personDao = personDao;
        this.ms = ms;
    }

    @Override
    public String writeName() {

        if (person != null)
            return ms.getMessage("user.tested.name", null, Locale.getDefault()) + " " + person.getName();
        else return null;
    }

    @Override
    public String writeSurname() {
        if (person != null)
            return ms.getMessage("user.tested.surname", null, Locale.getDefault()) + " " + person.getSurName();
        else return null;
    }

    @Override
    public String writeFullName() {
        if (person != null)
            return person.getName() + " " + person.getSurName();
        else return null;
    }

    @Override
    public void login(String name, String surName) throws Exception {
        try {
            if (personDao != null) {
                person = this.personDao.findByNameAndSurname(name, surName);
            }
        } catch (PersonDaoException ex) {
            ConsoleHelper.writeMessage(ms.getMessage("user.tested.repeat.input", null, Locale.getDefault()));
        }
    }
}
