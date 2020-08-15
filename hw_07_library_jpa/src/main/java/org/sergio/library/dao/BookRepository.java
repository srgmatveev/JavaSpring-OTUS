package org.sergio.library.dao;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("bookRepository")
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByBookName(String Name);

    List<Book> findByBookNameStartingWith(String prefix);

    @Query("SELECT b FROM Book b inner join b.genres genre WHERE genre.genreId = :id")
    List<Book> getBooksByGenreId(@Param("id") Long id);


    @Query("SELECT b FROM Book b inner join b.genres genre WHERE genre.genreId = :id")
    List<Book> getBooksByGenreIdSort(@Param("id") Long id, Sort sort);

    @Query("SELECT b FROM Book b inner join b.authors author WHERE author.authorId = :id")
    List<Book>  getBooksByAuthorId(@Param("id") Long id);

    @Query("SELECT b FROM Book b inner join b.authors author WHERE author.authorId = :id")
    List<Book>  getBooksByAuthorIdSort(@Param("id") Long id , Sort sort);
}
