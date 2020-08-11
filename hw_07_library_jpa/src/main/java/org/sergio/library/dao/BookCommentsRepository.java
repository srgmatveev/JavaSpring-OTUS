package org.sergio.library.dao;

import org.sergio.library.domain.BookComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bookCommentsRepo")
public interface BookCommentsRepository extends JpaRepository<BookComments, Long> {
}
