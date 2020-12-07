package org.sergio.library.service;

import org.sergio.library.domain.Book;
import org.sergio.library.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface BookDTOService {

    Optional<BookDTO> createBookDTO(String name);

}
