package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookCommentsRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("shellBookService")
public class ShellBookService implements BookService {
    final private AuthorRepository authorRepository;
    final private BookRepository bookRepository;
    final private GenreRepository genreRepository;
    final private BookCommentsRepository bookCommentsRepository;

    public ShellBookService(@Qualifier("authorRepo") AuthorRepository authorRepository,
                            @Qualifier("bookRepo") BookRepository bookRepository,
                            @Qualifier("genreRepo") GenreRepository genreRepository,
                            @Qualifier("bookCommentsRepo") BookCommentsRepository bookCommentsRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookCommentsRepository = bookCommentsRepository;
    }


    @Override
    @Transactional
    public Map<Genre, List<Book>> getBooksForAllGenres() {
        Map<Genre, List<Book>> map =
                new TreeMap<>(new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return o1.getGenreName().compareTo(o2.getGenreName());
                    }
                });
        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        for (int i = 0; i < genres.size(); i++) {
            Genre genre = genres.get(i);
              List<Book> books = bookRepository.getBooksByGenreIdSort(genre.getGenreId(),
                    Sort.by(Sort.Direction.ASC, "bookName"));
            map.put(genre, books);
        }

        return map;
    }

    @Override
    public List<Book> getBooksByAuthor(String name, String surName) {
        List<Book> books = new ArrayList<>();
        List<Author> authors = authorRepository.findByAuthorNameAndAuthorSurName(name, surName);
        if (authors != null && authors.size() > 0) {
            Author author = authors.get(0);
            books.addAll(authorRepository.getBooksByAuthorId(author.getAuthorId()));
        }
        return books;
    }

    @Override
    public Map<Author, List<Book>> getBooksForAllAuthors() {
        Map<Author, List<Book>> map =
                new TreeMap<>(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        int cmp = o1.getAuthorSurName().compareTo(o2.getAuthorSurName());
                        if (cmp != 0) return cmp;
                        else return o1.getAuthorName().compareTo(o2.getAuthorName());
                    }
                });
        List<Author> authors = (List<Author>) authorRepository.findAll();
        for (
                int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            List<Book> books = new ArrayList<>();
            books.addAll(authorRepository.getBooksByAuthorId(author.getAuthorId()));

            books.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getBookName().compareTo(o2.getBookName());
                }
            });

            map.put(author, books);
        }

        return map;
    }

    @Override
    public Book newBook(String name) {
        Book book = new Book();
        book.setBookName(name);
        return book;
    }

    @Override
    @Transactional
    public boolean addComments(Book book, Set<BookComments> bookComments) {
        if (book.getBookId() == null) {
            book = bookRepository.save(book);
        }
        boolean added = book.addBookComments(bookComments);
        for (BookComments comment : bookComments)
            bookCommentsRepository.save(comment);
        return added;
    }

    @Override
    public boolean addComment(Book book, BookComments comment) {
        if (book.getBookId() == null)
            save(book);
        boolean saved = book.addBookComment(comment);
        bookCommentsRepository.save(comment);
        return saved;
    }

    @Override
    @Transactional
    public Book addAuthor(Book book, Author author) {
        if (author == null || author.getAuthorId() == null) return book;
        if (book.getBookId() == null)
            save(book);
        book = bookRepository.findById(book.getBookId()).get();
        Optional<Author> getAuthor = authorRepository.findById(author.getAuthorId());
        if (getAuthor.isPresent()) {
            book.getAuthors().add(getAuthor.get());
            bookRepository.save(book);
        }
        return book;
    }

    @Override
    @Transactional
    public Book addGenre(Book book, Genre genre) {
        if (genre == null || genre.getGenreId() == null) return book;
        if (book.getBookId() == null)
            save(book);
        book = bookRepository.findById(book.getBookId()).get();
        Optional<Genre> getGenre = genreRepository.findById(genre.getGenreId());
        if (getGenre.isPresent()) {
            book.getGenres().add(getGenre.get());
            bookRepository.save(book);
        }
        return book;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

}
