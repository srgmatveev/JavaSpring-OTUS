package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.BookComments;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookCommentsRepo")
public interface BookCommentsRepository extends JpaRepository<BookComments, Long> {
    List<BookComments> findByBook(Book book, Sort registeredAt);
}
