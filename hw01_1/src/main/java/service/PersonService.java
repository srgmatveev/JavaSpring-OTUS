package service;

import domain.Person;

public interface PersonService {
    Person getByName(String name);
}
