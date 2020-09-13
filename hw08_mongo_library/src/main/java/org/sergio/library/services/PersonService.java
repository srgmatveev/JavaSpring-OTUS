package org.sergio.library.services;

import org.sergio.library.domain.Person;

public interface PersonService {
    Person login(String name, String surName) throws Exception;
}
