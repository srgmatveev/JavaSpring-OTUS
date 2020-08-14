package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(exclude={"authors", "genres"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = "book_id")
    @Setter(AccessLevel.NONE)
    private Long bookId;
    private String bookName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_genre_ref",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<Genre>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_author_ref",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<Author>();


    @OneToMany(mappedBy = "book")
    Set<BookComments> comments = new HashSet<BookComments>();
    ;

    public boolean addBookComment(BookComments comment) {
        comment.setBook(this);
        return this.getComments().add(comment);
    }

    public boolean addBookComments(Set<BookComments> comments) {
        for (BookComments comment : comments) {
            comment.setBook(this);
        }
        return this.getComments().addAll(comments);
    }


}
