package org.sergio.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.AuthorDTO;
import org.sergio.library.dto.BookDTO;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.repository.BookRepo;
import org.sergio.library.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookDTOServiceImplTest {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    BookDTOServiceImpl bookDTOService;

    @BeforeEach
    void setUp() {
        bookRepo.deleteAll();
        authorRepo.deleteAll();
        genreRepo.deleteAll();
    }

    @Test
    void createBookDTO() {
        Genre genre = new Genre("fantasy");
        Genre genre1 = new Genre("history");
        genreRepo.save(genre);
        genreRepo.save(genre1);
        assertNotNull(genre.getId());
        assertNotNull(genre1.getId());
        Author author = new Author("Mark", "Twain");
        authorRepo.save(author);
        assertNotNull(author.getId());
        Book book = new Book("Pipec");
        book.setGenres_ids(Arrays.asList(genre.getId(), genre1.getId()));
        book.setAuthors_ids(Arrays.asList(author.getId()));
        bookRepo.save(book);
        assertNotNull(book.getId());
        assertEquals(book.getGenres_ids().size(), 2);
        assertEquals(book.getAuthors_ids().size(), 1);

    }

    @Test
    @SuppressWarnings("unchecked")
    void fillGenres() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Book book = new Book("Helloid");
        bookRepo.save(book);
        assertNotNull(book.getId());
        List<String> list = book.getGenres_ids();
        assertNotNull(list);
        assertEquals(list.size(), 0);

        Method fillGenres = bookDTOService.getClass().getDeclaredMethod("fillGenres", book.getClass());
        fillGenres.setAccessible(true);
        List<GenreDTO> lst = (List<GenreDTO>) fillGenres.invoke(bookDTOService, book);
        assertEquals(lst.size(), 0);
        Book book1 = null;
        lst = (List<GenreDTO>) fillGenres.invoke(bookDTOService, book1);
        assertEquals(lst.size(), 0);
        bookRepo.deleteAll();
        book = new Book("Helloid1");

        Genre genre = new Genre("aaa");
        Genre genre1 = new Genre("bbb");
        genreRepo.save(genre);
        genreRepo.save(genre1);
        book.setGenres_ids(Arrays.asList(genre.getId(), genre1.getId()));
        bookRepo.save(book);
        Optional<BookDTO> bookDTO = bookDTOService.createBookDTO(book.getName());
        assertTrue(bookDTO.isPresent());
        assertEquals(bookDTO.get().getGenres().size(), 2);
        assertEquals(bookDTO.get().getGenres().get(0).getName(), "aaa");
        assertEquals(bookDTO.get().getGenres().get(1).getName(), "bbb");
    }

    @Test
    @SuppressWarnings("unchecked")
    void fillAuthors() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Book book = new Book("Helloid");
        bookRepo.save(book);
        assertNotNull(book.getId());
        List<String> list = book.getAuthors_ids();
        assertNotNull(list);
        assertEquals(list.size(), 0);
        Method fillAuthors = bookDTOService.getClass().getDeclaredMethod("fillAuthors", book.getClass());
        fillAuthors.setAccessible(true);
        List<AuthorDTO> lst = (List<AuthorDTO>) fillAuthors.invoke(bookDTOService, book);
        assertEquals(lst.size(), 0);
        Book book1 = null;
        lst = (List<AuthorDTO>) fillAuthors.invoke(bookDTOService, book1);
        assertEquals(lst.size(), 0);
        bookRepo.deleteAll();

        book = new Book("Helloid1");

        Author author = new Author("aaa", "aaa1");
        Author author1 = new Author("bbb", "bbb1");
        authorRepo.save(author);
        authorRepo.save(author1);
        book.setAuthors_ids(Arrays.asList(author.getId(), author1.getId()));
        bookRepo.save(book);
        Optional<BookDTO> bookDTO = bookDTOService.createBookDTO(book.getName());
        assertTrue(bookDTO.isPresent());
        assertEquals(bookDTO.get().getAuthors().size(), 2);
        assertEquals(bookDTO.get().getAuthors().get(0).getName(), "aaa");
        assertEquals(bookDTO.get().getAuthors().get(0).getSurName(), "aaa1");
        assertEquals(bookDTO.get().getAuthors().get(1).getName(), "bbb");
        assertEquals(bookDTO.get().getAuthors().get(1).getSurName(), "bbb1");
    }

    /*
    @Test
    @SuppressWarnings("unchecked")
    void fillAuthors() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Book book = new Book("Helloid");
        bookRepo.save(book);
        assertNotNull(book.getId());
        List<String> list = book.getAuthors_ids();
        assertNotNull(list);
        assertEquals(list.size(), 0);

        Class[] parameterType = {Book.class, org.springframework.data.mongodb.repository.MongoRepository.class, java.util.List.class};
        Method fillDTO = bookDTOService.getClass().getDeclaredMethod("fillDTO", parameterType);
        fillDTO.setAccessible(true);
        List<AuthorDTO> lst = (List<AuthorDTO>) fillDTO.invoke(bookDTOService
                , book
                , authorRepo
                , book.getAuthors_ids());

        assertEquals(lst.size(), 0);
        lst = (List<AuthorDTO>) fillDTO.invoke(bookDTOService
                , null
                , authorRepo
                , null);
        assertEquals(lst.size(), 0);
        bookRepo.deleteAll();

        book = new Book("Helloid1");

        Author author = new Author("aaa");
        Author author1 = new Author("bbb");
        authorRepo.save(author);
        authorRepo.save(author1);
        book.setAuthors_ids(Arrays.asList(author.getId(), author1.getId()));
        bookRepo.save(book);
        /* Arrays.stream(bookDTOService.getClass()
                .getDeclaredMethods())
                .forEach(x -> System.out.println(x));
*/
        //Method fillDTO = bookDTOService.getClass().getDeclaredMethod("fillDTO", book.getClass());
        //fillDTO.setAccessible(true);
/*
    }
    */


}