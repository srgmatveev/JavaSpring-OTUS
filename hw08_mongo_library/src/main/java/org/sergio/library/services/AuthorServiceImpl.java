package org.sergio.library.services;

import org.sergio.library.domain.Author;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {
    final private AuthorMongoRepository authorRepository;

    public AuthorServiceImpl(@Qualifier("authorRepo") AuthorMongoRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(String name, String surName) {
        return authorRepository.uniqSave(new Author(name, surName));
    }
}
