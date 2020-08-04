package org.sergio.library.service;

import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("shellLibService")
public class ShellLibraryService implements LibraryService {
    final private AuthorRepository authorRepository;
    final private BookRepository bookRepository;
    final private GenreRepository genreRepository;

    public ShellLibraryService(@Qualifier("authorRepo") AuthorRepository authorRepository,
                               @Qualifier("bookRepo") BookRepository bookRepository,
                               @Qualifier("genreRepo") GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public List<Book> getBooksByGenre(String name) {
        List<Book> books = new ArrayList<>();
        List<Genre> genres = genreRepository.findByGenreName(name);
        if (genres != null && genres.size() > 0) {
            Genre genre = genres.get(0);
            books = genreRepository.findBooksbyId(genre.getGenreId());
        }
        return books;
    }

    @Override
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
            List<Book> books = genreRepository.findBooksbyId(genre.getGenreId());

            books.sort(new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getBookName().compareTo(o2.getBookName());
                }
            });

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
            books = authorRepository.findBooksbyId(author.getAuthorId());
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
            List<Book> books = authorRepository.findBooksbyId(author.getAuthorId());

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
}
