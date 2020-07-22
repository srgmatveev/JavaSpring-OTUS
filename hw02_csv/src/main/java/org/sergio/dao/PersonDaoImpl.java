package org.sergio.dao;

import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonDaoImpl implements PersonDao {
    private List<Person> people = new ArrayList<Person>();

    private final MessageSource messageSource;

    public PersonDaoImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        people = new ArrayList<>();
    }

    @Override
    public Person findByNameAndSurname(String name, String surname) throws PersonDaoException {
        if (!isCorrect(name, surname)) {
            throw new PersonDaoException(
                    messageSource.getMessage("person.dao.exception.empty", null, Locale.getDefault())
            );
        }
        Person person = new Person(name, surname);
        if (people.contains(person))
            return people.stream().filter(x -> x.equals(person)).findFirst().get();
        else {
            people.add(person);
            return person;
        }
    }

    private boolean isCorrect(String name, String surName) {
        if (!isStringCorrect(name) || !isStringCorrect(surName)) return false;
        return true;
    }

    private boolean isStringCorrect(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        return true;
    }
}
