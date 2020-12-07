package org.sergio.library.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @NonNull
    @Indexed(name = "book_name_index")
    private String name;

    @Field("authors_ids")
    @Indexed(name = "authors_ids_index")
    private List<String> authors_ids = new ArrayList<>();

    @Field("genres_ids")
    @Indexed(name = "genres_ids_index")
    private List<String> genres_ids = new ArrayList<>();

    private String cover_id;
}
