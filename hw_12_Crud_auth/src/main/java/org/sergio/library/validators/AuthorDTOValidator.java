package org.sergio.library.validators;

import org.sergio.library.domain.Author;
import org.sergio.library.dto.AuthorDTO;
import org.sergio.library.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AuthorDTOValidator implements Validator {
    private final AuthorRepo autorRepo;
    private final MessageSource ms;

    public AuthorDTOValidator(@Qualifier("authorRepo") AuthorRepo autorRepo, MessageSource ms) {
        this.autorRepo = autorRepo;
        this.ms = ms;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthorDTO.class.isAssignableFrom(clazz);
    }

    @Transactional
    @Override
    public void validate(Object target, Errors errors) {
        AuthorDTO authorDTO = (AuthorDTO) target;
        String name = authorDTO.getName();
        String surName = authorDTO.getSurName();
        boolean hasErrors = false;
        int nameLen = name.length();
        if (nameLen < 1 || nameLen > 80) {
            errors.rejectValue("name",
                    "name.length",
                    new Object[]{nameLen},
                    ms.getMessage("author.validation.name.length.error", null, LocaleContextHolder.getLocale()));
            hasErrors = true;
        }
        int surNameLen = surName.length();
        if (surNameLen < 1 || surNameLen > 80) {
            errors.rejectValue("surName",
                    "surName.length",
                    new Object[]{surNameLen},
                    ms.getMessage("author.validation.surname.length.error", null, LocaleContextHolder.getLocale()));
            hasErrors = true;
        }
        if (hasErrors) return;

        String tmpName = authorDTO.getName();
        String tmpSurName = authorDTO.getSurName();
        String id = authorDTO.getId();
        Author author = autorRepo.uniqSave(new Author(tmpName, tmpSurName));
        authorDTO.setName(author.getName());
        authorDTO.setSurName(author.getSurName());
        if(id==null){
            authorDTO.setId(null);
        } else if(!author.getId().equals(id)){
            autorRepo.deleteById(id);
            authorDTO.setId(author.getId());
        }
    }
}
