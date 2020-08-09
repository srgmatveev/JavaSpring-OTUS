package org.sergio.library.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_comments")
@Data
public class BookComments {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @ManyToOne
    @JoinColumn(name = "person_id")
    Person person;

    LocalDateTime registeredAt;


}
