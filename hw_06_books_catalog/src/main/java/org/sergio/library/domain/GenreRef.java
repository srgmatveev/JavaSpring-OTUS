package org.sergio.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("BOOK_GENRE")
@Data
@AllArgsConstructor
public class GenreRef {
    private Long genreId;
    private Long bookId;

}
