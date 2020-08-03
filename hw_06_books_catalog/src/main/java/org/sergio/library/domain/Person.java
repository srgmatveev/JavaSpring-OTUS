package org.sergio.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
public class Person {
    @Id
    private Long personId;
    private String name;
    private String surName;

    public Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }
}
