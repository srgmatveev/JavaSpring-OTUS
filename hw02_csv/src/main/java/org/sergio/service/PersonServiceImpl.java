package org.sergio.service;

import org.sergio.domain.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "personService")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
