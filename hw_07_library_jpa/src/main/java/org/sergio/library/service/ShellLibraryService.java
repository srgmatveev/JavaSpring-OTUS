package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("shellLibService")
public class ShellLibraryService implements LibraryService {
    final private AuthorRepository authorRepository;
    final private BookRepository bookRepository;
    final private GenreRepository genreRepository;
    final private ShellBookService bookService;

    public ShellLibraryService(@Qualifier("authorRepo") AuthorRepository authorRepository,
                               @Qualifier("bookRepo") BookRepository bookRepository,
                               @Qualifier("genreRepo") GenreRepository genreRepository,
                               @Qualifier("shellBookService") ShellBookService bookService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }

    @Override
    public Map<Genre, List<Book>> getBooksForAllGenres() {
        return bookService.getBooksForAllGenres();
    }
}
