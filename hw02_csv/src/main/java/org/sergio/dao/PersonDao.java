package org.sergio.dao;

import org.sergio.domain.Person;
import org.sergio.exceptions.PersonDaoException;

public interface PersonDao {
    Person findByNameAndSurname(String name, String surname) throws PersonDaoException;
}
