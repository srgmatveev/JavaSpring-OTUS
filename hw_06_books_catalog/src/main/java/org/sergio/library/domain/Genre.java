package org.sergio.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Table
@Data
public class Genre {
    @Id
    private Long genreId;
    private String genreName;
}
