package org.sergio.library.dao;

import org.hibernate.Hibernate;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.Optional;

@Repository(value = "authorRepo")
public class AuthorRepo implements AuthorRepository {
    private AuthorRepository ar;

    public AuthorRepo(@Qualifier("authorRepository") AuthorRepository ar) {
        this.ar = ar;
    }

    @Override
    public List<Author> findByAuthorNameAndAuthorSurName(String Name, String authorSurName) {
        return ar.findByAuthorNameAndAuthorSurName(Name, authorSurName);
    }

    @Override
    public List<Author> findByAuthorName(String Name) {
        return ar.findByAuthorName(Name);
    }

    @Override
    public List<Author> findByAuthorNameStartingWith(String prefix) {
        return ar.findByAuthorNameStartingWith(prefix);
    }

    @Override
    public List<Author> findByAuthorSurName(String SurName) {
        return ar.findByAuthorSurName(SurName);
    }

    @Override
    public List<Author> findByAuthorSurNameStartingWith(String prefix) {
        return ar.findByAuthorSurNameStartingWith(prefix);
    }

    @Override
    public List<Author> findByAuthorNameStartingWithAndAuthorSurNameStartingWith(String Name, String SurName) {
        return ar.findByAuthorNameStartingWithAndAuthorSurNameStartingWith(Name, SurName);
    }

    @Override
    public Set<Book> getBooksByAuthorId(Long id) {
        return ar.getBooksByAuthorId(id);
    }


    @Override
    @SuppressWarnings("unchecked")
    public <S extends Author> S save(S entity) {
        List<Author> list = findByAuthorNameAndAuthorSurName(entity.getAuthorName(), entity.getAuthorSurName());
        S author = null;
        if (list != null) {
            if (list.size() == 0)
                author = ar.save(entity);
            else {
                author = (S) ar.findById(list.get(0).getAuthorId()).get();
                if (entity.getBooks() != null)
                    if (entity.getBooks().size() > 0)
                        author.setBooks(entity.getBooks());
            }
        }
        return author;
    }

    @Override
    public <S extends Author> Iterable<S> saveAll(Iterable<S> entities) {
        return ar.saveAll(entities);
    }

    @Override
    public Optional<Author> findById(Long aLong) {
        return ar.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return ar.existsById(aLong);
    }

    @Override
    public Iterable<Author> findAll() {
        return ar.findAll();
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> longs) {
        return ar.findAllById(longs);
    }

    @Override
    public long count() {
        return ar.count();
    }

    @Override
    public void deleteById(Long aLong) {
        ar.deleteById(aLong);
    }

    @Override
    public void delete(Author entity) {
        ar.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Author> entities) {
        ar.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        ar.deleteAll();
    }
}
