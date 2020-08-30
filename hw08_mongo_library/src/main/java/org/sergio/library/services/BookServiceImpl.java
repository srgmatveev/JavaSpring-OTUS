package org.sergio.library.services;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.sergio.library.repositories.BookMongoRepository;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Transactional
    public Map<Genre, List<Book>> getBooksForAllGenres() {
        Map<Genre, List<Book>> map =
                new TreeMap<>(new Comparator<Genre>() {
                    @Override
                    public int compare(Genre o1, Genre o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        for (int i = 0; i < genres.size(); i++) {
            Genre genre = genres.get(i);
            List<Book> books = bookRepository.getBooksByGenreIdSort(genre.getId(),
                    Sort.by(Sort.Direction.ASC, "name"));
            map.put(genre, books);
        }

        return map;
    }

    @Override
    @Transactional
    public List<Book> getBooksByAuthor(String name, String surName) {
        List<Book> books = new ArrayList<>();
        Author author = authorRepository.findByNameAndSurName(name, surName);
        if (author != null) {
            books = bookRepository.getBooksByAuthorIdSort(author.getId(),
                    Sort.by(Sort.Direction.ASC, "name"));
        }
        return books;
    }

    @Override
    @Transactional
    public Map<Author, List<Book>> getBooksForAllAuthors() {
        Map<Author, List<Book>> map =
                new TreeMap<>(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        int cmp = o1.getSurName().compareTo(o2.getSurName());
                        if (cmp != 0) return cmp;
                        else return o1.getName().compareTo(o2.getName());
                    }
                });
        List<Author> authors = (List<Author>) authorRepository.findAll();
        for (
                int i = 0; i < authors.size(); i++) {
            Author author = authors.get(i);
            List<Book> books = bookRepository.getBooksByAuthorIdSort(author.getId(), Sort.by(Sort.Direction.ASC, "name"));
            map.put(author, books);
        }

        return map;

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
