package org.sergio.library.dao;

import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("genreRepo")
public class GenreRepo implements GenreRepository {

    private GenreRepository gr;

    public GenreRepo(@Qualifier("genreRepository") GenreRepository gr) {
        this.gr = gr;
    }

    @Override
    public List<Genre> findByGenreName(String name) {
        return gr.findByGenreName(name);
    }

    @Override
    public List<Genre> findByGenreNameStartingWith(String prefix) {
        return gr.findByGenreNameStartingWith(prefix);
    }

    @Override
    public List<Book> findBooksbyId(Long id) {
        return gr.findBooksbyId(id);
    }

    @Override
    public <S extends Genre> S save(S entity) {
        List<Genre> list = gr.findByGenreName(entity.getGenreName());
        S genre = null;
        if (list != null) {
            if (list.size() == 0)
                genre = gr.save(entity);
            else genre = entity;
        }
        return genre;
    }

    @Override
    public <S extends Genre> Iterable<S> saveAll(Iterable<S> entities) {
        return gr.saveAll(entities);
    }

    @Override
    public Optional<Genre> findById(Long aLong) {
        return gr.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return gr.existsById(aLong);
    }

    @Override
    public Iterable<Genre> findAll() {
        return gr.findAll();
    }

    @Override
    public Iterable<Genre> findAllById(Iterable<Long> longs) {
        return gr.findAllById(longs);
    }

    @Override
    public long count() {
        return gr.count();
    }

    @Override
    public void deleteById(Long aLong) {
        gr.deleteById(aLong);
    }

    @Override
    public void delete(Genre entity) {
        gr.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Genre> entities) {
        gr.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        gr.deleteAll();
    }
}
