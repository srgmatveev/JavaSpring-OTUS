package org.sergio.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table
@Data
public class Author {
    @Id
    private Long authorId;
    private String authorName;
    @Column(value="AUTHOR_SURNAME")
    private String authorSurName;

}

