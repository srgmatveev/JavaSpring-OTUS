package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookCommentsRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service("shellLibService")
public class ShellLibraryService implements LibraryService {
    final private AuthorRepository authorRepository;
    final private BookRepository bookRepository;
    final private BookCommentsRepository commentsRepository;
    final private GenreRepository genreRepository;
    final private ShellBookService bookService;

    public ShellLibraryService(@Qualifier("authorRepo") AuthorRepository authorRepository,
                               @Qualifier("bookRepo") BookRepository bookRepository,
                               @Qualifier("genreRepo") GenreRepository genreRepository,
                               @Qualifier("shellBookService") ShellBookService bookService,
                               @Qualifier("bookCommentsRepo") BookCommentsRepository commentsRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookService = bookService;
        this.commentsRepository = commentsRepository;
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

    @Override
    @Transactional(readOnly = true)
    public String showComments(String bookName) {
        List<Book> books = bookRepository.findByBookName(bookName);
        if (books == null || books.size() == 0) return "not.book.found";
        Book book = books.get(0);
        if (book == null) return "not.book.found";
        StringBuilder result = new StringBuilder("");

        result.append(book.getBookName());
        result.append(":\n");
        List<BookComments> list = commentsRepository.findByBook(book, Sort.by(Sort.Direction.ASC, "registeredAt"));
        for (int i = 0; i < list.size(); i++) {
            BookComments comment = list.get(i);
            String str;
            if (comment.getPerson() != null) {
                str = String.format("\t%s at %s by %s%n", comment.getMessage(),
                        comment.getRegisteredAt(),
                        comment.getPerson().getName() + " " + comment.getPerson().getSurName());

            } else {
                String pers = "anonymous";
                str = String.format("\t%s at %s by %s%n", comment.getMessage(),
                        comment.getRegisteredAt(),
                        pers);
            }
            result.append(str);
        }

        return result.toString();
    }
}
