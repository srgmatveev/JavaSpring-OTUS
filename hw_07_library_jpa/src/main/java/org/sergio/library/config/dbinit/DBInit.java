package org.sergio.library.config.dbinit;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.service.AuthorService;
import org.sergio.library.service.BookService;
import org.sergio.library.service.GenreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Profile("dev")
public class DBInit implements DatasourceConfig {
    private final GenreService genreService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final MessageSource messageSource;

    public DBInit(@Qualifier("shellGenreService") GenreService genreService,
                  @Qualifier("shellBookService") BookService bookService,
                  @Qualifier("shellAuthorService") AuthorService authorService, MessageSource messageSource) {
        this.genreService = genreService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.messageSource = messageSource;
    }

    @Override
    public void setup() {
        Genre genreFantasy = newGenre(messageSource.getMessage("genre.fantasy", null, Locale.getDefault()));
        Genre genreAdventure = newGenre(messageSource.getMessage("genre.adventure", null, Locale.getDefault()));
        Genre genreMistery = newGenre(messageSource.getMessage("genre.mistery", null, Locale.getDefault()));
        Genre genreEducation = newGenre(messageSource.getMessage("genre.education", null, Locale.getDefault()));
        Genre genreHistorical = newGenre(messageSource.getMessage("genre.historical", null, Locale.getDefault()));

        Author Lev_Tolstoy = newAuthor("Лев", "Толстой");
        Author Jack_London = newAuthor("Джек", "Лондон");
        Author Lev_Landay = newAuthor("Лев", "Ландау");
        Author Ray_Bradbury = newAuthor("Рэй", "Бредбери");
        Author Josef_Livshitz = newAuthor("Иосиф", "Лифшиц");
        Author Mark_Twain = newAuthor("Марк", "Твен");

        Book bookPeaceAndWar = newBook("Война и мир");
        bookService.addGenre(bookPeaceAndWar, genreAdventure);
        bookService.addGenre(bookPeaceAndWar, genreHistorical);
        bookService.addAuthor(bookPeaceAndWar, Lev_Tolstoy);

        Book TheoryOfFileld = newBook("Теория поля");
        bookService.addGenre(TheoryOfFileld, genreEducation);
        bookService.addAuthor(TheoryOfFileld, Lev_Landay);
        bookService.addAuthor(TheoryOfFileld, Josef_Livshitz);

        Book YankeeInKingArthurCourt = newBook("Янки при дворе короля Артура");
        bookService.addGenre(YankeeInKingArthurCourt, genreFantasy);
        bookService.addGenre(YankeeInKingArthurCourt, genreAdventure);
        bookService.addAuthor(YankeeInKingArthurCourt, Mark_Twain);

        Book quantumMechanics = newBook("Квантовая механика");
        bookService.addGenre(quantumMechanics, genreEducation);
        bookService.addAuthor(quantumMechanics, Lev_Landay);
        bookService.addAuthor(quantumMechanics, Josef_Livshitz);

        Book fahrenheit_451 = newBook("451 градус по Фаренгейту");
        bookService.addGenre(fahrenheit_451, genreFantasy);
        bookService.addAuthor(fahrenheit_451, Ray_Bradbury);

        Book whiteFang = newBook("Белый клык");
        bookService.addGenre(whiteFang, genreAdventure);
        bookService.addAuthor(whiteFang, Jack_London);

        Book martinEden = newBook("Мартин Иден");
        bookService.addGenre(martinEden, genreHistorical);
        bookService.addAuthor(martinEden, Jack_London);
    }

    private Genre newGenre(String name) {
        return genreService.addGenre(name);
    }

    private Book newBook(String name) {
        Book book = bookService.newBook(name);
        bookService.save(book);
        return book;
    }

    private Author newAuthor(String name, String surName) {
        return authorService.addAuthor(name, surName);
    }
}
