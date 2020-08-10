package org.sergio.library.dao;

import org.sergio.library.domain.BookComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentsTestRepo")
public interface BookCommentsTestRepository extends JpaRepository<BookComments, Long> {
    List<BookComments> findAll();
}
