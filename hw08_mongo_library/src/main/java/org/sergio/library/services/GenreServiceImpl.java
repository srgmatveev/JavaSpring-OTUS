package org.sergio.library.services;

import org.sergio.library.domain.Genre;
import org.sergio.library.repositories.GenreMongoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "genreService")
public class GenreServiceImpl implements GenreService {
    private final GenreMongoRepository genreMongoRepository;

    public GenreServiceImpl(@Qualifier("genreRepo") GenreMongoRepository genreMongoRepository) {
        this.genreMongoRepository = genreMongoRepository;
    }

    @Override
    public Genre addGenre(String name) {
        return genreMongoRepository.save(new Genre(name));
    }
}
