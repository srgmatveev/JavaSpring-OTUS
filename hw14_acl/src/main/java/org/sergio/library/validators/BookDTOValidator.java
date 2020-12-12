package org.sergio.library.validators;

import org.sergio.library.domain.Book;
import org.sergio.library.dto.BookDTO;
import org.sergio.library.repository.BookRepo;
import org.sergio.library.service.BookDTOService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookDTOValidator implements Validator {
    @Autowired
    private BookDTOService  bookDTOService;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MessageSource ms;

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDTO.class.isAssignableFrom(clazz);
    }

    @Override
    @Transactional
    public void validate(Object target, Errors errors) {
        BookDTO bookDTO = (BookDTO) target;
        String name = bookDTO.getName();
        boolean hasErrors = false;
        int nameLen = name.length();
        if (nameLen < 1 || nameLen > 80) {
            errors.rejectValue("name",
                    "name.length",
                    new Object[]{nameLen},
                    ms.getMessage("book.validation.name.length.error", null, LocaleContextHolder.getLocale()));
            hasErrors = true;
        }

        if (hasErrors) return;

        Book book = new Book(bookDTO.getName());
        bookDTOService.convertBookDTOtoBook(bookDTO, book);

        //BeanUtils.copyProperties(bookDTO, book);
        bookRepo.save(book);
        bookDTO.setId(book.getId());
    }
}
