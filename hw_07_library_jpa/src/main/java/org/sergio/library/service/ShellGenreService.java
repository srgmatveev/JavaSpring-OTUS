package org.sergio.library.service;

import org.sergio.library.dao.GenreRepository;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("shellGenreService")
public class ShellGenreService implements GenreService{
    final private GenreRepository genreRepository;

    public ShellGenreService(@Qualifier("genreRepo") GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre addGenre(String name) {
        Genre genre = new Genre();
        genre.setGenreName(name);
        genre = genreRepository.save(genre);
        return genre;
    }
}
