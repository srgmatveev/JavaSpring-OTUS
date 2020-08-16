package org.sergio.library.service;

import org.sergio.library.domain.*;

import java.util.List;
import java.util.Map;

public interface LibraryService {
    Map<Genre, List<Book>> getBooksForAllGenres();
    List<Book> getBooksByAuthor(String name, String surName);

    Map<Author, List<Book>> getBooksForAllAuthors();

    boolean addComment(String bookName, String comment, Person person);
    boolean addAnoneComment(String bookName, String comment);

    String showComments(String bookName);
}
