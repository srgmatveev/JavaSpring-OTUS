package org.sergio.library.dao;

import org.sergio.library.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorTestRepository extends CrudRepository<Author,Long> {
}
