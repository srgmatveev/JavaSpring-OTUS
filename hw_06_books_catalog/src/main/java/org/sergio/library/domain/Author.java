package org.sergio.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
public class Author {
    @Id
    private Long authorId;
    private String authorName;
    private String authorSurName;
    @MappedCollection(idColumn = "AUTHOR_ID")
    private Set<AuthorRef> books = new HashSet<>();
    public void addBooks(Book book) {
        books.add(new AuthorRef(authorId, book.getBookId()));
    }
}
