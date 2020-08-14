package org.sergio.library.dao;

import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "bookRepo")
public class BookRepo implements BookRepository {
    final private BookRepository br;

    public BookRepo(@Qualifier("bookRepository") BookRepository br) {
        this.br = br;
    }

    @Override
    public List<Book> findByBookName(String Name) {
        return br.findByBookName(Name);
    }

    @Override
    public List<Book> findByBookNameStartingWith(String prefix) {
        return br.findByBookNameStartingWith(prefix);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S extends Book> S save(S entity) {
        List<Book> list = br.findByBookName(entity.getBookName());
        S book = null;
        if (list != null) {
            if (list.size() == 0)
                book = br.save(entity);
            else {
                book = (S) br.findById(list.get(0).getBookId()).get();
                if (entity.getAuthors() != null)
                    if (entity.getAuthors().size() > 0)
                        book.setAuthors(entity.getAuthors());
                if (entity.getGenres() != null)
                    if (entity.getGenres().size() > 0)
                        book.setGenres(entity.getGenres());
            }
        }
        return book;
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
        return br.saveAll(entities);
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return br.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return br.existsById(aLong);
    }

    @Override
    public Iterable<Book> findAll() {
        return br.findAll();
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> longs) {
        return br.findAllById(longs);
    }

    @Override
    public long count() {
        return br.count();
    }

    @Override
    public void deleteById(Long aLong) {
        br.deleteById(aLong);
    }

    @Override
    public void delete(Book entity) {
        br.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        br.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        br.deleteAll();
    }
}
