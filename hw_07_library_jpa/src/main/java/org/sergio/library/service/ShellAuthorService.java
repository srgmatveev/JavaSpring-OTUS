package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.domain.Author;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shellAuthorService")
public class ShellAuthorService implements AuthorService {
    final private AuthorRepository authorRepository;

    public ShellAuthorService(@Qualifier("authorRepo") AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author addAuthor(String name, String surName) {
        Author author = new Author();
        author.setAuthorName(name);
        author.setAuthorSurName(surName);
        author = authorRepository.save(author);
        return author;
    }
}
