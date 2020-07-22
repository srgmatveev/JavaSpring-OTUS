package org.sergio.service;

import org.sergio.domain.Person;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "simplePersonService")
@Scope("prototype")
public class PersonServiceSimpleImpl implements PersonService {
    private Person person;

    public PersonServiceSimpleImpl(Person person) {
        this.person = person;
    }

    @Override
    public String writeName() {
        return person.getName();
    }

    @Override
    public String writeSurname() {
        return person.getSurName();
    }
}
