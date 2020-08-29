package org.sergio.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.repositories.AuthorMongoRepository;
import org.sergio.library.repositories.BookMongoRepository;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookServiceTest {
    private final BookService bookService;
    private final AuthorMongoRepository authorMongoRepository;
    private final BookMongoRepository bookMongoRepository;
    private final GenreMongoRepository genreMongoRepository;

    BookServiceTest(@Qualifier("bookService") BookService bookService,
                    @Qualifier("authorRepo") AuthorMongoRepository authorMongoRepository,
                    @Qualifier("bookRepo") BookMongoRepository bookMongoRepository,
                    @Qualifier("genreRepo") GenreMongoRepository genreMongoRepository) {
        this.bookService = bookService;
        this.bookMongoRepository = bookMongoRepository;
        this.authorMongoRepository = authorMongoRepository;
        this.genreMongoRepository = genreMongoRepository;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBooksForAllGenres() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBooksForAllAuthors() {
    }

    @Test
    void newBook() {
        Book book = bookService.newBook("Mister Twister");
        assertEquals(book.getName(), "Mister Twister");
        assertNull(book.getId());
    }

    @Test
    @Transactional
    void addAuthor() {
        Book book = bookService.newBook("Война и мир");
        Author author = new Author("Лев", "Толстой");
        author = authorMongoRepository.save(author);
        book = bookService.addAuthor(book, author);
        Book book1 = bookService.addAuthor(book, author);
        assertEquals(book.getId(), book1.getId());
        Author author1 = new Author("Тоже с длиинной", "бородой");
        author1 = authorMongoRepository.save(author1);
        book = bookService.addAuthor(book, author1);
        assertEquals(book.getAuthors_ids().size(), 2);
        assertEquals(book.getAuthors_ids().toString(),
                "[" + author.getId() + ", " + author1.getId() + "]");
    }

    @Test
    @Transactional
    void addGenre() {
        Book book = bookService.newBook("Война и мир");
        Genre genre = new Genre("приключения");
        genre = genreMongoRepository.save(genre);
        book = bookService.addGenre(book, genre);
        Book book1 = bookService.addGenre(book, genre);
        assertEquals(book.getId(), book1.getId());
        Genre genre1 = new Genre("приключения");
        genre1 = genreMongoRepository.save(genre1);
        book = bookService.addGenre(book, genre1);
        assertEquals(book.getGenres_ids().size(), 2);
        assertEquals(book.getGenres_ids().toString(),
                "[" + genre.getId() + ", " + genre1.getId() + "]");
        Genre genre2 = new Genre("фантастика");
        genre2 = genreMongoRepository.save(genre2);
        book = bookService.addGenre(book, genre2);
        assertEquals(book.getGenres_ids().size(), 3);
        assertEquals(book.getGenres_ids().toString(),
                "[" + genre.getId() + ", " + genre1.getId() + ", " + genre2.getId() + "]");
    }


    @Test
    @Transactional
    void save() {
        Book book = bookService.newBook("Война и мир");
        book = bookService.save(book);
        assertEquals(book.getName(), "Война и мир");
    }
}