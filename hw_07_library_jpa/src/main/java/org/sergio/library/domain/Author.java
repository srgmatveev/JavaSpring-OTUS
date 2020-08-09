package org.sergio.library.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "author")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_gen")
    @SequenceGenerator(name = "author_gen", sequenceName = "author_seq", allocationSize = 1)
    @Column(name = "author_id")
    private Long authorId;
    private String authorName;
    @Column(name = "AUTHOR_SURNAME")
    private String authorSurName;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Book> books;

}
