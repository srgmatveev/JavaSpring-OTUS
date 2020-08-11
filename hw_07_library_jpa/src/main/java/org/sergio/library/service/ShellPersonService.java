package org.sergio.library.service;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.dao.PersonRepository;
import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "shellPersonService")
public class ShellPersonService implements PersonService {
    private PersonRepository personRepository;
    private MessageSource ms;

    public ShellPersonService(@Qualifier("personRepo") PersonRepository personRepository, MessageSource ms) {
        this.personRepository = personRepository;
        this.ms = ms;
    }

    @Override
    public void login(String name, String surName) throws Exception {
        Person person;
        if (personRepository != null) {
            List<Person> list = this.personRepository.findByNameAndSurName(name, surName);
            if (list != null && !list.isEmpty()) {
                person = list.get(0);
            } else {
                person = new Person(name, surName);
                personRepository.save(person);
            }
        }

    }
}
