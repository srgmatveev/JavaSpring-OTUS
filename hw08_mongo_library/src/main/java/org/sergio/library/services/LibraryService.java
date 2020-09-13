package org.sergio.library.services;

import org.sergio.library.domain.*;

import java.util.List;
import java.util.Map;

public interface LibraryService {
    Map<Genre, List<Book>> getBooksForAllGenres();
    List<Book> getBooksByAuthor(String name, String surName);
    Map<Author, List<Book>> getBooksForAllAuthors();
}
