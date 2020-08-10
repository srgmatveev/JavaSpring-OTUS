package org.sergio.library.dao;

import org.sergio.library.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("genreTestRepo")
public interface GenreTestRepository extends JpaRepository<Genre, Long> {
}
