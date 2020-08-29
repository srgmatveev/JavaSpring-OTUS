package org.sergio.library.services;

import org.sergio.library.domain.Author;

public interface AuthorService {
    Author addAuthor(String name, String surName);
}
