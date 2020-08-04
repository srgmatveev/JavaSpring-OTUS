package org.sergio.library.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthorRepoTest {
    private AuthorRepository authorRepository;

    private BookTestRepository bookRepository;
    private GenreTestRepository genreRepository;

    private Genre genre1;
    private Genre genre2;
    private Genre genre3;
    private Genre genre4;
    private Author author1;
    private Author author2;
    private Author author3;
    private Author author4;
    private Author author5;
    private Book book1;
    private Book book2;
    private Book book3;

    @Autowired
    public AuthorRepoTest(@Qualifier("authorRepo") AuthorRepository authorRepository,
                          BookTestRepository bookRepository, GenreTestRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        init();
    }


    private void init() {
        genre1 = genreRepository.save(getGenre1());
        genre2 = genreRepository.save(getGenre2());
        genre3 = genreRepository.save(getGenre3());
        genre4 = genreRepository.save(getGenre4());
        author1 = authorRepository.save(getAuthor1());
        author2 = authorRepository.save(getAuthor2());
        author3 = authorRepository.save(getAuthor3());
        author4 = authorRepository.save(getAuthor4());
        author5 = authorRepository.save(getAuthor5());

        book1 = getBook1();
        book1.addAuthor(author2);
        book1.addAuthor(author3);
        book1.addGenre(genre2);
        bookRepository.save(book1);

        book2 = getBook2();
        book2.addAuthor(author4);
        book2.addGenre(genre1);
        book2.addGenre(genre3);
        bookRepository.save(book2);

        book3 = getBook3();
        book3.addAuthor(author1);
        book3.addGenre(genre3);
        bookRepository.save(book3);

    }

    @Test
    void findByAuthorNameStartingWith() {
        List<Author> authors = authorRepository.findByAuthorNameStartingWith("Ма");
        assertEquals(authors.size(), 2);
        for (int i = 0; i < authors.size(); i++) {
            assertEquals(authors.get(i).getAuthorName(), "Марк");
        }
    }


    @Test
    void findByAuthorNameStartingWithAndAuthorSurNameStartingWith() {
        List<Author> authors = authorRepository.findByAuthorNameStartingWithAndAuthorSurNameStartingWith("Ма", "Т");
        assertEquals(authors.size(), 1);
        for (int i = 0; i < authors.size(); i++) {
            assertEquals(authors.get(i).getAuthorName(), "Марк");
            assertEquals(authors.get(i).getAuthorSurName(), "Твен");
        }
    }

    private static Book getBook1() {
        Book book = new Book();
        book.setBookName("Теория поля");
        return book;
    }

    private static Book getBook2() {
        Book book = new Book();
        book.setBookName("Янки при дворе Короля Артура");
        return book;
    }

    private static Book getBook3() {
        Book book = new Book();
        book.setBookName("451 градус по Фаренгейту");
        return book;
    }

    private static Genre getGenre1() {
        Genre genre = new Genre();
        genre.setGenreName("фантастика");
        return genre;
    }

    private static Genre getGenre2() {
        Genre genre = new Genre();
        genre.setGenreName("Физика");
        return genre;
    }

    private static Genre getGenre3() {
        Genre genre = new Genre();
        genre.setGenreName("приключения");
        return genre;
    }

    private static Genre getGenre4() {
        Genre genre = new Genre();
        genre.setGenreName("история");
        return genre;
    }

    private static Author getAuthor1() {
        Author author = new Author();
        author.setAuthorName("Рэй");
        author.setAuthorSurName("Брэдбери");
        return author;
    }

    private static Author getAuthor2() {
        Author author = new Author();
        author.setAuthorName("Лев");
        author.setAuthorSurName("Ландау");
        return author;
    }

    private static Author getAuthor3() {
        Author author = new Author();
        author.setAuthorName("Иосиф");
        author.setAuthorSurName("Лифшиц");
        return author;
    }

    private static Author getAuthor4() {
        Author author = new Author();
        author.setAuthorName("Марк");
        author.setAuthorSurName("Твен");
        return author;
    }

    private static Author getAuthor5() {
        Author author = new Author();
        author.setAuthorName("Марк");
        author.setAuthorSurName("Аврелий");
        return author;
    }

}