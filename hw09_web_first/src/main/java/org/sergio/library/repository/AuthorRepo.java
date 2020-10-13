package org.sergio.library.repository;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Genre;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("authorRepo")
public interface AuthorRepo extends MongoRepository<Author, String> {
    @Cacheable("authors")
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    List<Author> findByName(final String Name);

    @Cacheable("authors")
    @Query("{ 'surName' : {$regex: '^?0$', $options: 'i' }}")
    List<Author> findBySurName(final String surName);

    @Cacheable("authors")
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }, 'surName' : {$regex: '^?1$', $options: 'i' }}")
    Author findByNameAndSurName(final String Name, final String surName);

    default Author uniqSave(Author author) {
        if (author == null) return null;
        Author author1 = findByNameAndSurName(author.getName(), author.getSurName());
        if (author1 == null) {
            author1 = this.save(author);
        }
        return author1;
    }
}
