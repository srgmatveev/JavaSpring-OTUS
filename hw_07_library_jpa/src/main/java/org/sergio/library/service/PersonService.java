package org.sergio.library.service;

import org.sergio.library.domain.Person;

public interface PersonService {
    Person login(String name, String surName) throws Exception;
}
