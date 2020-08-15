package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository(value = "genreRepository")
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByGenreName(String name);

    List<Genre> findByGenreNameStartingWith(String prefix);

}
