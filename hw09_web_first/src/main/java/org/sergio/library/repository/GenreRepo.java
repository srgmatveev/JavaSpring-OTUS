package org.sergio.library.repository;

import org.sergio.library.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("genreRepo")
public interface GenreRepo extends MongoRepository<Genre, String> {
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Genre findByName(final String Name);

    default Genre uniqSave(Genre genre) {
        Genre genre1 = findByName(genre.getName());
        if (genre1 == null) {
            genre1 = this.save(genre);
        }
        return genre1;
    }

}