package org.sergio.service;

import org.sergio.domain.Person;

public class PersonServiceImpl implements PersonService {
    private Person person;

    public PersonServiceImpl(Person person) {
        this.person = person;
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
}
