package org.sergio.library.service;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;

import java.util.List;
import java.util.Map;

public interface LibraryService {
    Map<Genre, List<Book>> getBooksForAllGenres();

}
