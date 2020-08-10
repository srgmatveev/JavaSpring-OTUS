package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_comments")
@Data
public class BookComments {
    @Id
    @Setter(AccessLevel.NONE)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    private LocalDateTime registeredAt;

    private String message;


}
