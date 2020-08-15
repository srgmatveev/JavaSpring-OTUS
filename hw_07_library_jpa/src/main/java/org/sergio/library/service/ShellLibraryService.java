package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookCommentsRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                               @Qualifier("shellBookService") ShellBookService bookService,
                               @Qualifier("BookCommentsRepository") BookCommentsRepository commentsRepository) {
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

    @Override
    @Transactional
    public boolean addComment(String bookName, String comment, Person person) {
        List<Book> books = bookRepository.findByBookName(bookName);
        if (books == null || books.size() == 0) return false;
        Book book = books.get(0);
        if (book == null) return false;
        if (person == null || person.getPersonId() == null) return false;
        if (comment == null || comment.isBlank()) return false;

        BookComments bookComment = new BookComments();
        bookComment.setMessage(comment);
        bookComment.setPerson(person);

        return bookService.addComment(book, bookComment);
    }

    @Override
    @Transactional
    public boolean addAnoneComment(String bookName, String comment) {
        List<Book> books = bookRepository.findByBookName(bookName);
        if (books == null || books.size() == 0) return false;
        Book book = books.get(0);
        if (book == null) return false;
        if (comment == null || comment.isBlank()) return false;

        BookComments bookComment = new BookComments();
        bookComment.setMessage(comment);
        return bookService.addComment(book, bookComment);
    }
}
