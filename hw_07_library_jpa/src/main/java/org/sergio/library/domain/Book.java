package org.sergio.library.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = "book_id")
    private Long bookId;
    private String bookName;

    @ManyToMany
    @JoinTable(
            name = "book_genre_ref",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author_ref",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;

    @OneToMany(mappedBy = "book")
    Set<BookComments> comments;

}
