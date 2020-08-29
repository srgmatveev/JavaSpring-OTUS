package org.sergio.library.services;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.sergio.library.repositories.BookMongoRepository;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {
    final private AuthorMongoRepository authorRepository;
    final private BookMongoRepository bookRepository;
    final private GenreMongoRepository genreRepository;

    public BookServiceImpl(@Qualifier("authorRepo") AuthorMongoRepository authorRepository,
                           @Qualifier("bookRepo") BookMongoRepository bookRepository,
                           @Qualifier("genreRepo") GenreMongoRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Map<Genre, List<Book>> getBooksForAllGenres() {
        return null;
    }

    @Override
    public List<Book> getBooksByAuthor(String name, String surName) {
        return null;
    }

    @Override
    public Map<Author, List<Book>> getBooksForAllAuthors() {
        return null;
    }

    @Override
    public Book newBook(String name) {
        return new Book(name);
    }

    @Override
    @Transactional
    public Book addAuthor(Book book, Author author) {
        if (author == null || author.getId() == null) return book;
        if (book.getId() == null)
            book = save(book);
        Optional<Book> getBook = bookRepository.findById(book.getId());
        if (getBook.isEmpty()) return null;
        book = getBook.get();
        Optional<Author> getAuthor = authorRepository.findById(author.getId());
        if (getAuthor.isPresent()) {
            String authorId = getAuthor.get().getId();
            List<String> authorsList = book.getAuthors_ids();
            if (!authorsList.contains(authorId))
                authorsList.add(authorId);
            book = bookRepository.save(book);
        }
        return book;
    }

    @Override
    @Transactional
    public Book addGenre(Book book, Genre genre) {

        if (genre == null || genre.getId() == null) return book;
        if (book.getId() == null)
            book = save(book);
        Optional<Book> getBook = bookRepository.findById(book.getId());
        if (getBook.isEmpty()) return null;
        book = getBook.get();
        Optional<Genre> getGenre = genreRepository.findById(genre.getId());
        if (getGenre.isPresent()) {
            String genreId = getGenre.get().getId();
            List<String> genresList = book.getGenres_ids();
            if (!genresList.contains(genreId))
                genresList.add(genreId);
            book = bookRepository.save(book);
        }
        return book;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.uniqSave(book);
    }
}
