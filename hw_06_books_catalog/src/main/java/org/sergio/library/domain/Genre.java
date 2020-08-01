package org.sergio.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
public class Genre {
    @Id
    private Long genreId;
    private String genreName;
    @MappedCollection(idColumn = "GENRE_ID")
    private Set<GenreRef> books = new HashSet<>();

    public void addBooks(Book book) {
        books.add(new GenreRef(genreId, book.getBookId()));
    }
}
