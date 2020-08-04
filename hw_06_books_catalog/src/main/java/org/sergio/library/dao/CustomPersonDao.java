package org.sergio.library.dao;

import org.sergio.library.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("customPersonDao")
public class CustomPersonDao implements PersonDao {
    private PersonDao personDao;

    public CustomPersonDao(@Qualifier("personDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public <S extends Person> S save(S person) {
        List<Person> list = findByNameAndSurName(person.getName(), person.getSurName());
        S myPerson = null;
        if (list != null) {
            if (list.size() == 0)
                myPerson = personDao.save(person);
            else
                myPerson = person;
        }
        return myPerson;
    }

    @Override
    public <S extends Person> Iterable<S> saveAll(Iterable<S> entities) {
        return personDao.saveAll(entities);
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return personDao.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return personDao.existsById(aLong);
    }

    @Override
    public Iterable<Person> findAll() {
        return null;
    }

    @Override
    public Iterable<Person> findAllById(Iterable<Long> longs) {
        return personDao.findAllById(longs);
    }

    @Override
    public long count() {
        return personDao.count();
    }

    @Override
    public void deleteById(Long aLong) {
        personDao.deleteById(aLong);
    }

    @Override
    public void delete(Person entity) {
        personDao.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Person> entities) {
        personDao.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        personDao.deleteAll();
    }

    @Override
    public List<Person> findByNameAndSurName(String name, String surName) {
        return personDao.findByNameAndSurName(name, surName);
    }
}
