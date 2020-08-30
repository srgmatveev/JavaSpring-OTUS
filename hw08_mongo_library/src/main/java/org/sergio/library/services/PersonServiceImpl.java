package org.sergio.library.services;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Person;
import org.sergio.library.repositories.PersonMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "personService")
public class PersonServiceImpl implements PersonService {
    private PersonMongoRepository personRepository;
    private MessageSource ms;

    public PersonServiceImpl(@Qualifier("personRepo") PersonMongoRepository personRepository, MessageSource ms) {
        this.personRepository = personRepository;
        this.ms = ms;
    }

    @Override
    public Person login(String name, String surName) throws Exception {
        Person person = null;
        if (personRepository != null) {
            List<Person> list = this.personRepository.findByNameAndSurName(name, surName);
            if (list != null && !list.isEmpty()) {
                person = list.get(0);
            } else {
                person = new Person(name, surName);
                personRepository.save(person);
            }
        }
        return person;
    }
}
