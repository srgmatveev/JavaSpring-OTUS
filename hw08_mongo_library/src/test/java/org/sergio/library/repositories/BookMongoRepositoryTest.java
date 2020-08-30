package org.sergio.library.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.domain.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookMongoRepositoryTest {
    private final BookMongoRepository bookMongoRepository;

    BookMongoRepositoryTest(@Qualifier("bookRepo") BookMongoRepository bookMongoRepository) {
        this.bookMongoRepository = bookMongoRepository;
    }

    @Test
    @Transactional
    void getBooksByAuthorId() {
        Book book = new Book("Hello kitty");
        book.getAuthors_ids().add("1");
        book.getAuthors_ids().add("2");
        book = bookMongoRepository.save(book);
        book = bookMongoRepository.findByName("Hello kitty");
        assertEquals(book.getAuthors_ids().size(), 2);
        assertTrue(book.getAuthors_ids().contains("1"));
        assertTrue(book.getAuthors_ids().contains("2"));
        assertFalse(book.getAuthors_ids().contains("3"));
        Book book1 = new Book("War in the world");
        book1.getAuthors_ids().add("2");
        book1 = bookMongoRepository.save(book1);
        assertEquals(book1.getAuthors_ids().size(), 1);
        assertTrue(book1.getAuthors_ids().contains("2"));

        Book book2 = new Book("Dishes forever");
        book2.getAuthors_ids().add("1");
        book2 = bookMongoRepository.save(book2);
        assertEquals(book2.getAuthors_ids().size(), 1);
        assertTrue(book2.getAuthors_ids().contains("1"));

        List<Book> books = bookMongoRepository.getBooksByAuthorId("1");
        assertNotNull(books);
        assertEquals(books.size(), 2);
        assertEquals(books.toString(),
                "[Book(id=" + book.getId() + ", name=Hello kitty, authors_ids=[1, 2], genres_ids=[]), Book(id=" + book2.getId() + ", name=Dishes forever, authors_ids=[1], genres_ids=[])]");
    }

    @Test
    void findByName() {
    }


    @Test
    void uniqSave() {
    }

    @Test
    @Transactional
    void getBooksByAuthorIdSort() {
        Book book = new Book("Hello kitty");
        book.getAuthors_ids().add("1");
        book.getAuthors_ids().add("2");
        book = bookMongoRepository.save(book);
        book = bookMongoRepository.findByName("Hello kitty");
        assertEquals(book.getAuthors_ids().size(), 2);
        assertTrue(book.getAuthors_ids().contains("1"));
        assertTrue(book.getAuthors_ids().contains("2"));
        assertFalse(book.getAuthors_ids().contains("3"));
        Book book1 = new Book("War in the world");
        book1.getAuthors_ids().add("2");
        book1 = bookMongoRepository.save(book1);
        assertEquals(book1.getAuthors_ids().size(), 1);
        assertTrue(book1.getAuthors_ids().contains("2"));

        Book book2 = new Book("Dishes forever");
        book2.getAuthors_ids().add("1");
        book2 = bookMongoRepository.save(book2);
        assertEquals(book2.getAuthors_ids().size(), 1);
        assertTrue(book2.getAuthors_ids().contains("1"));

        List<Book> books = bookMongoRepository.getBooksByAuthorIdSort("1", Sort.by(Sort.Direction.ASC, "name"));
        assertNotNull(books);
        assertEquals(books.size(), 2);
        assertEquals(books.toString(),
                "[Book(id=" + book2.getId() + ", name=Dishes forever, authors_ids=[1], genres_ids=[]), Book(id=" + book.getId() + ", name=Hello kitty, authors_ids=[1, 2], genres_ids=[])]");

    }

    @Test
    @Transactional
    void getBooksByGenreIdSort() {
        Book book = new Book("Hello kitty");
        book.getGenres_ids().add("1");
        book.getGenres_ids().add("2");
        book = bookMongoRepository.save(book);
        book = bookMongoRepository.findByName("Hello kitty");
        assertEquals(book.getGenres_ids().size(), 2);
        assertTrue(book.getGenres_ids().contains("1"));
        assertTrue(book.getGenres_ids().contains("2"));
        assertFalse(book.getGenres_ids().contains("3"));
        Book book1 = new Book("War in the world");
        book1.getGenres_ids().add("2");
        book1 = bookMongoRepository.save(book1);
        assertEquals(book1.getGenres_ids().size(), 1);
        assertTrue(book1.getGenres_ids().contains("2"));

        Book book2 = new Book("Dishes forever");
        book2.getGenres_ids().add("1");
        book2 = bookMongoRepository.save(book2);
        assertEquals(book2.getGenres_ids().size(), 1);
        assertTrue(book2.getGenres_ids().contains("1"));

        List<Book> books = bookMongoRepository.getBooksByGenreIdSort("1", Sort.by(Sort.Direction.ASC, "name"));
        assertNotNull(books);
        assertEquals(books.size(), 2);
        assertEquals(books.toString(),
                "[Book(id=" + book2.getId() + ", name=Dishes forever, authors_ids=[], genres_ids=[1]), Book(id=" + book.getId() + ", name=Hello kitty, authors_ids=[], genres_ids=[1, 2])]");

    }
}