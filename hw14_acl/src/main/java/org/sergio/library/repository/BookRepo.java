package org.sergio.library.repository;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepo extends MongoRepository<Book, String> {
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Book findByName(final String Name);
}
