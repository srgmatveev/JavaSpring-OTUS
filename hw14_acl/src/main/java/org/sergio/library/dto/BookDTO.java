package org.sergio.library.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookDTO {
    private String id;
    private String name;
    private List<AuthorDTO> authors = new ArrayList<>();
    private List<GenreDTO> genres = new ArrayList<>();
}
