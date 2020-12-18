package org.sergio.library.repository;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.BookDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BookRepo extends MongoRepository<Book, String> {
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Book findByName(final String Name);

    @Query("{ 'genres_ids' : {$regex: '^?0$', $options: 'i' }}")
    List<Book> findByGenreId(final String genreID);
}
