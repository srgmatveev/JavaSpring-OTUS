package org.sergio.service;

public interface PersonService {
    String writeName();

    String writeSurname();

    String writeFullName();

    void login(String name, String surName) throws Exception;
}
