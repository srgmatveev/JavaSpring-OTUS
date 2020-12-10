package org.sergio.library.service;

import org.sergio.library.domain.Book;

import java.util.Optional;

public interface BookService {
    Optional<Book> addAuthor(String bookId, String authorId);
}
