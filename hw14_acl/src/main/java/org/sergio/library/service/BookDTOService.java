package org.sergio.library.service;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Book;
import org.sergio.library.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface BookDTOService {

    Optional<BookDTO> createBookDTO(String name);
    void convertBooktoBookDTO(Book book, BookDTO bookDTO);

    void convertBookDTOtoBook(BookDTO bookDTO, Book book);
}
