package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTestRepository extends CrudRepository<Book, Long> {
}
