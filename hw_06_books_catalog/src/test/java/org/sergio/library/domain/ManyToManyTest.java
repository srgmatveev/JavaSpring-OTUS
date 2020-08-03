package org.sergio.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorTestRepository;
import org.sergio.library.dao.BookTestRepository;
import org.sergio.library.dao.GenreTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

//@ContextConfiguration( classes = { ManyConfig.class } )
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ManyToManyTest {

    @Autowired
    private AuthorTestRepository authorRepository;

    @Autowired
    private BookTestRepository bookRepository;

    @Autowired
    private GenreTestRepository genreRepository;

    @Test
    @DisplayName("many-to-many-mapping-test")
    void embeddedMappingTest() {

        Genre genre1 = genreRepository.save(getGenre1());
        Genre genre2 = genreRepository.save(getGenre2());
        Genre genre3 = genreRepository.save(getGenre3());
        Genre genre4 = genreRepository.save(getGenre4());
        Author author1 = authorRepository.save(getAuthor1());
        Author author2 = authorRepository.save(getAuthor2());
        Author author3 = authorRepository.save(getAuthor3());
        Author author4 = authorRepository.save(getAuthor4());

        Book book1 =getBook1();
        book1.addAuthor(author2);
        book1.addAuthor(author3);
        book1.addGenre(genre2);
        Book createdBook1 = bookRepository.save(book1);
        assertTrue(createdBook1!=null);

        Book book2 =getBook2();
        book2.addAuthor(author4);
        book2.addGenre(genre1);
        book2.addGenre(genre3);
        Book createdBook2 = bookRepository.save(book2);
        assertTrue(createdBook2!=null);

        Book book3 =getBook3();
        book3.addAuthor(author1);
        book3.addGenre(genre3);
        Book createdBook3 = bookRepository.save(book3);
        assertTrue(createdBook3!=null);

        assertTrue(bookRepository.existsById(book1.getBookId()));
        bookRepository.delete(book1);
        assertTrue(!bookRepository.existsById(book1.getBookId()));

        bookRepository.findAll().forEach(b -> System.err.println(b));
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
        author.setAuthorName("Маррк");
        author.setAuthorSurName("Твен");
        return author;
    }
}