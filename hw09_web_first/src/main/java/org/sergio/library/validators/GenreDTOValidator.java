package org.sergio.library.validators;

import org.sergio.library.domain.Genre;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class GenreDTOValidator implements Validator {
    private final GenreRepo repo;
    private final MessageSource ms;
    public GenreDTOValidator(@Qualifier("genreRepo") GenreRepo repo, MessageSource ms) {
        this.repo = repo;
        this.ms = ms;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return GenreDTO.class.isAssignableFrom(clazz);

    }

    @Override
    @Transactional
    public void validate(Object target, Errors errors) {
        GenreDTO genreDTO = (GenreDTO) target;
        String name = genreDTO.getName();
        int len = name.length();
        if (len < 3 || len > 80) {
            errors.rejectValue("name",
                    "name.length",
                    new Object[]{len},
                    ms.getMessage("genre.validation.length.error", null, LocaleContextHolder.getLocale()));
            return;
        }
        String tmpName = genreDTO.getName().toLowerCase();
        String id = genreDTO.getId();
        Genre genre = repo.uniqSave(new Genre(tmpName));
        genreDTO.setName(genre.getName());
        if (id == null) {
            genreDTO.setId(null);
        } else {
            if (genre.getId() != id) {
                repo.deleteById(id);
                genreDTO.setId(genre.getId());
            }
        }
    }
}
