package org.sergio.library.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@RequiredArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    @NonNull
    private String name;
    @DBRef(lazy = true)
    @Field("authors_ids")
    @Indexed
    private List<Author> authors_ids;
    @DBRef(lazy = true)
    @Field("genres_ids")
    @Indexed
    private List<Genre> genres_ids;
}
