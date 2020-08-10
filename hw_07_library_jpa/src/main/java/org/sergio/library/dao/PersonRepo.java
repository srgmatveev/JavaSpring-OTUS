package org.sergio.library.dao;

import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("personRepo")
public class PersonRepo implements PersonRepository {
    private PersonRepository personRepository;

    public PersonRepo(@Qualifier("personRepository") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public <S extends Person> S save(S person) {
        List<Person> list = findByNameAndSurName(person.getName(), person.getSurName());
        S myPerson = null;
        if (list != null) {
            if (list.size() == 0)
                myPerson = personRepository.save(person);
            else
                myPerson = person;
        }
        return myPerson;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return personRepository.saveAll(entities);
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return personRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return personRepository.existsById(aLong);
    }

    @Override
    public Iterable<Person> findAll() {
        return null;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> longs) {
        return personRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        personRepository.deleteById(aLong);
    }

    @Override
    public void delete(Person entity) {
        personRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {
        personRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Override
    public List<Person> findByNameAndSurName(String name, String surName) {
        return personRepository.findByNameAndSurName(name, surName);
    }
}
