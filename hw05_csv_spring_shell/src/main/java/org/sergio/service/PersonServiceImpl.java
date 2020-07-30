package org.sergio.service;

import org.sergio.dao.PersonDao;
import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;
    private Person person;
    private String name;
    private String surName;
    private final MessageSource ms;
    private final boolean DEBUG;

    public PersonServiceImpl(PersonDao personDao, MessageSource ms, @Value("${app.debug}") boolean debug) throws IOException {
        this.personDao = personDao;
        this.ms = ms;
        DEBUG = debug;
        while (true)
            try {
                login();
                if (personDao != null) {
                    person = this.personDao.findByNameAndSurname(name, surName);
                }
                break;
            } catch (PersonDaoException ex) {
                ConsoleHelper.writeMessage(ms.getMessage("user.tested.repeat.input", null, Locale.getDefault()));
            }
    }

    private void login() throws IOException {
        if (DEBUG) {
            this.name = "Sergey";
            this.surName = "Matveev";
        } else {
            ConsoleHelper.writeMessage(ms.getMessage("user.tested.name", null, Locale.getDefault()));
            this.name = ConsoleHelper.readMessage();
            ConsoleHelper.writeMessage(ms.getMessage("user.tested.surname", null, Locale.getDefault()));
            this.surName = ConsoleHelper.readMessage();
        }
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
        throw new Exception("Not realized");
    }
}
