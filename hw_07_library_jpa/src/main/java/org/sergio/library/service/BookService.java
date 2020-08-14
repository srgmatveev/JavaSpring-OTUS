package org.sergio.library.service;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.sergio.library.domain.Genre;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {

    Map<Genre, List<Book>> getBooksForAllGenres();

    List<Book> getBooksByAuthor(String name, String surName);

    Map<Author, List<Book>> getBooksForAllAuthors();

    Book newBook(String name);

    boolean addComments(Book book, Set<BookComments> bookComments);

    boolean addComment(Book book, BookComments comment);

    Book addAuthor(Book book, Author author);

    boolean addGenre(Book book, Long id);

    Book save(Book book);
}
