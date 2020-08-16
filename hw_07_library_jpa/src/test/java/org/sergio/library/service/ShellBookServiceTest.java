package org.sergio.library.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sergio.library.dao.AuthorRepository;
import org.sergio.library.dao.BookCommentsRepository;
import org.sergio.library.dao.BookCommentsTestRepository;
import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema_test.sql"},
                config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED)),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = "classpath:drop_test.sql",
                config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED)
        )
})
class ShellBookServiceTest {
    private BookService bookService;
    private BookCommentsTestRepository commentsRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    public ShellBookServiceTest(@Qualifier("shellBookService") BookService bookService,
                                @Qualifier("commentsTestRepo") BookCommentsTestRepository commentsRepository,
                                @Qualifier("authorRepo") AuthorRepository authorRepository,
                                @Qualifier("genreRepo") GenreRepository genreRepository
    ) {
        this.bookService = bookService;
        this.commentsRepository = commentsRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
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
        Book book = bookService.newBook("Война и мир");
        book = bookService.save(book);
        assertEquals(book.getBookName(), "Война и мир");
    }

    @Test
    void addCommnets() {
        Book book = bookService.newBook("Война и мир");
        Set<BookComments> bookComments = new HashSet<>();
        BookComments bookComment1 = new BookComments();
        bookComment1.setMessage("отстой");
        BookComments bookComment2 = new BookComments();
        bookComment2.setMessage("полный");
        bookComments.add(bookComment1);
        bookComments.add(bookComment2);
        bookService.addComments(book, bookComments);

        assertEquals(book.getComments().size(), 2);
        Set<BookComments> bookCommentsNew = new HashSet<>();
        BookComments bookComment3 = new BookComments();
        bookComment3.setMessage("ужасно отстой");
        BookComments bookComment4 = new BookComments();
        bookComment4.setMessage("ужасно полный");
        bookCommentsNew.add(bookComment3);
        bookCommentsNew.add(bookComment4);
        bookService.addComments(book, bookCommentsNew);
        assertEquals(book.getComments().size(), 4);
        // bookService.save(book);
    }

    @Test
    void save() {
        Book book = bookService.newBook("Война и мир");
        book = bookService.save(book);
        assertEquals(book.getBookName(), "Война и мир");
    }


    @Test
    void addComment() {
        Book book = bookService.newBook("Война и мир");
        BookComments bookComment1 = new BookComments();
        bookComment1.setMessage("отстой");
        bookService.addComment(book, bookComment1);
        assertEquals(bookComment1.getBook().getBookId(), book.getBookId());
        BookComments bookComment2 = new BookComments();
        bookComment2.setMessage("полный");
        bookService.addComment(book, bookComment2);
        assertEquals(book.getComments().size(), 2);
    }

    @Test
    void addAuthor() {
        Book book = bookService.newBook("Война и мир");
        Author author = new Author();
        author.setAuthorName("Лев");
        author.setAuthorSurName("Толстой");
        author = authorRepository.save(author);
        book = bookService.addAuthor(book, author);
        book = bookService.addAuthor(book, author);
        assertEquals(book.getAuthors().toString(), "[Author{authorId=1, authorName='Лев', authorSurName='Толстой'}]");

        author = new Author();
        author.setAuthorName("Тоже с длиинной");
        author.setAuthorSurName("бородой");
        author = authorRepository.save(author);
        book = bookService.addAuthor(book, author);

        assertEquals(book.getAuthors().toString(),
                "[Author{authorId=1, authorName='Лев', authorSurName='Толстой'}, Author{authorId=2, authorName='Тоже с длиинной', authorSurName='бородой'}]");

    }

    @Test
    void addGenre() {
        Book book = bookService.newBook("Война и мир");
        Genre genre = new Genre();
        genre.setGenreName("приключения");
        genre = genreRepository.save(genre);
        book = bookService.addGenre(book, genre);
        book = bookService.addGenre(book, genre);
        Genre genre1 = new Genre();
        genre1.setGenreName("приключения");
        genreRepository.save(genre1);
        book = bookService.addGenre(book, genre1);
        assertEquals(book.getGenres().toString(), "[Genre{genreId=1, genreName='приключения'}]");
        Genre genre2 = new Genre();
        genre2.setGenreName("фантастика");
        genreRepository.save(genre2);
        book = bookService.addGenre(book, genre2);
        assertEquals(book.getGenres().toString(), "[Genre{genreId=1, genreName='приключения'}, Genre{genreId=2, genreName='фантастика'}]");
    }
}