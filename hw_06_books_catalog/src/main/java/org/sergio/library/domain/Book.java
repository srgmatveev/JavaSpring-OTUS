package org.sergio.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table
@Data
public class Book {
    @Id
    private Long bookId;
    private String bookName;
    @MappedCollection(idColumn = "BOOK_ID")
    private Set<AuthorRef> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(new AuthorRef(author.getAuthorId()));
    }

    @MappedCollection(idColumn = "BOOK_ID")
    private Set<GenreRef> genres = new HashSet<>();

    public void addGenre(Genre genre) {
        genres.add(new GenreRef(genre.getGenreId()));
    }

}
