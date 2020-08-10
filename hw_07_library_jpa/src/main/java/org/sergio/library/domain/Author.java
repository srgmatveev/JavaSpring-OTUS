package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @Setter(AccessLevel.NONE)
    private Long authorId;
    private String authorName;
    @Column(name = "AUTHOR_SURNAME")
    private String authorSurName;
    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    private Set<Book> books;

}
