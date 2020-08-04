package org.sergio.library.dao;

import org.sergio.library.domain.Author;
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

}
