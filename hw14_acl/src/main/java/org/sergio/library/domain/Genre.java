package org.sergio.library.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "genres")
public class Genre {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @NonNull
    @Indexed(name = "genre_name_index")
    private String name;
}
