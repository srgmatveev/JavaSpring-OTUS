package org.sergio.library.dao;

import org.sergio.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreTestRepository extends CrudRepository<Genre, Long> {
}
