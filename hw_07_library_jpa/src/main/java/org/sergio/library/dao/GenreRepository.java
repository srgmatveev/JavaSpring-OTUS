package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository(value = "genreRepository")
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByGenreName(String name);

    List<Genre> findByGenreNameStartingWith(String prefix);

    @Query("SELECT g.books FROM Genre g WHERE g.genreId = :id")
    public Set<Book> getBooksByGenreId(@Param("id") Long id);


    /*

    @Query("select * from ArticleEntity article inner join article.numbers number where article.numbers.volume=:volume and article.numbers.id=:id")
    List<ArticleEntity> findAll(@Param("volume") String volume , @Param("id") long id);

     */
   /* @Query(
            "SELECT * FROM BOOK WHERE BOOK_ID IN (SELECT BOOK_ID  FROM BOOK_GENRE  WHERE GENRE_ID= :id)"
    )
    List<Book> findBooksbyId(Long id);
*/
}
