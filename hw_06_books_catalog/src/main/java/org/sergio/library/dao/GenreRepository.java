package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "genreRepository")
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByGenreName(String name);

    List<Genre> findByGenreNameStartingWith(String prefix);

    /*

    @Query("select * from ArticleEntity article inner join article.numbers number where article.numbers.volume=:volume and article.numbers.id=:id")
    List<ArticleEntity> findAll(@Param("volume") String volume , @Param("id") long id);

     */
    @Query(
            "SELECT * FROM BOOK WHERE BOOK_ID IN (SELECT BOOK_ID  FROM BOOK_GENRE  WHERE GENRE_ID= :id)"
    )
    List<Book> findBooksbyId(Long id);

}
