package org.sergio.library.dao;

import org.sergio.library.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PersonDao extends CrudRepository<Person, Long> {
    List<Person> findByNameAndSurName(String name, String surName);
/*
    default Person mySave(Person person) {
        List<Person> list = findByNameAndSurName(person.getName(), person.getSurName());
        Person myPerson = person;
        if (list != null && list.size() == 0)
            myPerson = save(person);
        return myPerson;
    }*/
}
