package org.sergio.library.repositories;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "bookRepo")
public interface BookMongoRepository extends MongoRepository<Book, String> {
    @Query("{ 'name' : {$regex: '^?0$', $options: 'i' }}")
    Book findByName(final String Name);

    @Query("{ 'authors_ids' : {$regex: '^?0$', $options: 'i' }}")
    List<Book> getBooksByAuthorId(String id);

    @Query("{ 'authors_ids' : {$regex: '^?0$', $options: 'i' }}")
    List<Book> getBooksByAuthorIdSort(String id, Sort sort);

    default Book uniqSave(Book book) {
        Book book1 = findByName(book.getName());
        if (book1 == null) {
            book1 = this.save(book);
        }
        return book1;
    }

    @Query("{ 'genres_ids' : {$regex: '^?0$', $options: 'i' }}")
    List<Book> getBooksByGenreIdSort(String id, Sort bookName);
}
