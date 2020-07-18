package dao;

import domain.Person;

public interface PersonDao {

    Person findByName(String name);
}