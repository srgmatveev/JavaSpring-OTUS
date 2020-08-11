package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity
@Table(name = "book_comments")
@Data
@EqualsAndHashCode(exclude={"book"})
public class BookComments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_gen")
    @SequenceGenerator(name = "comments_gen", sequenceName = "comments_seq", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    private LocalDateTime registeredAt;

    private String message;

    public BookComments() {
        this.registeredAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return "BookComments{" +
                "id=" + id==null?"0":id +
                ", book={" + book.getBookId() + ", " + book.getBookName() + "}" +
                //", person=" + person +
                ", registeredAt=" + registeredAt +
                ", message='" + message + '\'' +
                '}';
    }


}
