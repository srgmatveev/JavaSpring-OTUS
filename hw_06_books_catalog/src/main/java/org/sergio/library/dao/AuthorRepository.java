package org.sergio.library.dao;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "authorRepository")
public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findByAuthorNameAndAuthorSurName(String Name, String authorSurName);

    List<Author> findByAuthorName(String Name);

    List<Author> findByAuthorNameStartingWith(String prefix);

    List<Author> findByAuthorSurName(String SurName);

    List<Author> findByAuthorSurNameStartingWith(String prefix);

    List<Author> findByAuthorNameStartingWithAndAuthorSurNameStartingWith(String Name, String SurName);

    @Query(
            "SELECT * FROM BOOK WHERE BOOK_ID IN (SELECT BOOK_ID  FROM BOOK_AUTHOR  WHERE AUTHOR_ID=  :id)"
    )
    List<Book> findBooksbyId(Long id);

}
