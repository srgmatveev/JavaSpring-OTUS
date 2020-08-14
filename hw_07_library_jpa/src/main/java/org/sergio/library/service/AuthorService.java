package org.sergio.library.service;

import org.sergio.library.domain.Author;

public interface AuthorService {
    Author addAuthor(String name, String surName);
}
