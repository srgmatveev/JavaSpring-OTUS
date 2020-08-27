package org.sergio.library.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "persons")
public class Person {
    @Id
    String id;
    @NonNull
    private String name;
    @NonNull
    private String surName;
}
