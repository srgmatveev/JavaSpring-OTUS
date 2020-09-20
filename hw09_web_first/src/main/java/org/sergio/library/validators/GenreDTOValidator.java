package org.sergio.library.validators;

import org.sergio.library.domain.Genre;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GenreDTOValidator implements Validator {
    private final GenreRepo repo;

    public GenreDTOValidator(@Qualifier("genreRepo") GenreRepo repo) {
        this.repo = repo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return GenreDTO.class.isAssignableFrom(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        GenreDTO genreDTO = (GenreDTO) target;
        String name = genreDTO.getName();
        int len = name.length();
        if (len < 3 || len > 80) {
            errors.rejectValue("name",
                    "name.length",
                    new Object[]{len},
                    "Genre length should be between 3 and 80");
        }
        String tmpName = genreDTO.getName().toLowerCase();
        repo.uniqSave(new Genre(tmpName));
        genreDTO.setName(tmpName);
    }
}
