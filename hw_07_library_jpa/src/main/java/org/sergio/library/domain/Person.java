package org.sergio.library.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    @SequenceGenerator(name = "person_gen", sequenceName = "person_seq", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long personId;

    private String name;
    @Column(name = "sur_name")
    private String surName;

    public Person() {
    }

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    @OneToMany(mappedBy = "person")
    Set<BookComments> comments;


}
