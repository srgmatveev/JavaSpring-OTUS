package org.sergio.library.service;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;

import java.util.List;
import java.util.Map;

public interface LibraryService {
    List<Book> getBooksByGenre(String name);

    Map<Genre, List<Book>> getBooksForAllGenres();

    List<Book> getBooksByAuthor(String name, String surName);

    Map<Author, List<Book>> getBooksForAllAuthors();

}
