package org.sergio.library.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    @NonNull
    @Indexed(name="authors_name_index")
    private String name;

    @NonNull
    @Indexed(name = "authors_surName_index")
    private String surName;
}
