package org.sergio.library.repositories;

import org.sergio.library.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "authorRepo")
public interface AuthorMongoRepository extends MongoRepository<Author, String> {
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Author findByName(final String Name);

    @Query("{ 'surName' : {$regex: '^?0$', $options: 'i' }}")
    Author findBySurName(final String surName);

    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }, 'surName' : {$regex: '^?1$', $options: 'i' }}")
    Author findByNameAndSurName(final String Name, final String surName);

    default Author uniqSave(Author author) {
        Author author1 = findByNameAndSurName(author.getName(), author.getSurName());
        if (author1 == null) {
            author1 = this.save(author);
        }
        return author1;
    }
}
