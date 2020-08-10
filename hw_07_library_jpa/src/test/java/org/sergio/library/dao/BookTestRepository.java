package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookTestRepo")
public interface BookTestRepository extends JpaRepository<Book, Long> {
}
