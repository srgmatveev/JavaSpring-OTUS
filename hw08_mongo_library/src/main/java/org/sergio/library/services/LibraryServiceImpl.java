package org.sergio.library.services;


import org.sergio.library.domain.*;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.sergio.library.repositories.BookMongoRepository;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service("libService")
public class LibraryServiceImpl implements LibraryService {
    final private AuthorMongoRepository authorRepository;
    final private BookMongoRepository bookRepository;
    final private GenreMongoRepository genreRepository;
    final private BookService bookService;

    public LibraryServiceImpl(@Qualifier("authorRepo") AuthorMongoRepository authorRepository,
                              @Qualifier("bookRepo") BookMongoRepository bookRepository,
                              @Qualifier("genreRepo") GenreMongoRepository genreRepository,
                              @Qualifier("bookService") BookService bookService
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
    }

    @Override
    public Map<Genre, List<Book>> getBooksForAllGenres() {
        return bookService.getBooksForAllGenres();
    }

    @Override
    public List<Book> getBooksByAuthor(String name, String surName) {
        return bookService.getBooksByAuthor(name, surName);
    }

    @Override
    public Map<Author, List<Book>> getBooksForAllAuthors() {
        return bookService.getBooksForAllAuthors();
    }

}
