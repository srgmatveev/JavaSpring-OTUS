package org.sergio.service;

import org.sergio.dao.PersonDao;
import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;
    private Person person;
    private String name;
    private String surName;
    private static final boolean DEBUG = true;

    public PersonServiceImpl(PersonDao personDao) throws IOException {
        this.personDao = personDao;
        while (true)
            try {
                login();
                if (personDao != null) {
                    person = this.personDao.findByNameAndSurname(name, surName);
                }
                break;
            } catch (PersonDaoException ex) {
                ConsoleHelper.writeMessage("Повторите ввод имени и фамилии");
            }
    }

    private void login() throws IOException {
        if (DEBUG) {
            this.name = "Sergey";
            this.surName = "Matveev";
        } else {
            ConsoleHelper.writeMessage("Введите имя");
            this.name = ConsoleHelper.readMessage();
            ConsoleHelper.writeMessage("Введите фамилию");
            this.surName = ConsoleHelper.readMessage();
        }
    }

    @Override
    public String writeName() {

        if (person != null)
            return "Имя тестируемого пользователя: " + person.getName();
        else return null;
    }

    @Override
    public String writeSurname() {
        if (person != null)
            return "Фамилия тестируемого пользователя: " + person.getSurName();
        else return null;
    }

    @Override
    public String writeFullName() {
        if (person != null)
            return person.getName() + " " + person.getSurName();
        else return null;
    }
}
