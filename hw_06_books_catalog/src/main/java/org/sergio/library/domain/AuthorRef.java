package org.sergio.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("BOOK_AUTHOR")
@Data
@AllArgsConstructor
public class AuthorRef {
    private Long authorId;
    //private Long bookId;
}
