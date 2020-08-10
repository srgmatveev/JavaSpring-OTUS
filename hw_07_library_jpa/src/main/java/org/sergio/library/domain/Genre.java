package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_gen")
    @SequenceGenerator(name="genre_gen", sequenceName="genre_seq", allocationSize = 1)
    @Column(name = "genre_id")
    @Setter(AccessLevel.NONE)
    private Long genreId;
    private String genreName;
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Book> books;
}
