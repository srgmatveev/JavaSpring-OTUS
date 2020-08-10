package org.sergio.library.dao;

import org.sergio.library.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>
{
    List<Person> findByNameAndSurName(String name, String surName);
}
