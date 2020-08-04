package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "genreRepository")
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByGenreName(String name);

    List<Genre> findByGenreNameStartingWith(String prefix);

}
