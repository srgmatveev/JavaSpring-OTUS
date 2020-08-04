package org.sergio.library.dao;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByBookName(String Name);

    List<Book> findByBookNameStartingWith(String prefix);

}
