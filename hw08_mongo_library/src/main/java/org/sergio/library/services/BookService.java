package org.sergio.library.services;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;

import java.util.List;
import java.util.Map;

public interface BookService {
    Map<Genre, List<Book>> getBooksForAllGenres();

    List<Book> getBooksByAuthor(String name, String surName);

    Map<Author, List<Book>> getBooksForAllAuthors();

    Book newBook(String name);

    Book addAuthor(Book book, Author author);

    Book addGenre(Book book, Genre genre);

    Book save(Book book);
}
