package org.sergio.library.dao;


import org.sergio.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authorTestRepo")
public interface AuthorTestRepository extends JpaRepository<Author, Long> {
}
