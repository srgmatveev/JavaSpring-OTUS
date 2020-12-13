package org.sergio.library.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.sergio.library.domain.Book;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.repository.BookRepo;
import org.sergio.library.repository.GenreRepo;
import org.sergio.library.repository.GridFSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private GridFSRepo gridFSRepo;

    @Override
    @Transactional
    public Optional<Book> addAuthor(String bookId, String authorId) {
        if (bookId.isBlank())
            return Optional.empty();
        if (authorId.isBlank())
            return Optional.empty();

        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (bookOptional.isPresent()) {
            List<String> authors_id = bookOptional.get().getAuthors_ids();
            if (addToList(authors_id, authorId) == true)
                bookRepo.save(bookOptional.get());
        }


        return bookOptional;
    }

    @Override
    public Optional<Book> addGenre(String bookId, String genreId) {
        if (bookId.isBlank())
            return Optional.empty();
        if (genreId.isBlank())
            return Optional.empty();

        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (bookOptional.isPresent()) {
            List<String> genres_id = bookOptional.get().getGenres_ids();
            if (addToList(genres_id, genreId) == true)
                bookRepo.save(bookOptional.get());
        }

        return bookOptional;
    }

    @Override
    public Optional<Book> addCover(String bookId, String coverId) {
        if (bookId.isBlank())
            return Optional.empty();
        if (coverId.isBlank())
            return Optional.empty();
        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (bookOptional.isPresent()) {
            GridFSFile gFile = gridFSRepo.findOne(coverId);
            if(gFile!=null) {
                bookOptional.get().setCover_id(gFile.getObjectId().toString());
                bookRepo.save(bookOptional.get());
            }
        }

        return bookOptional;
    }

    private boolean addToList(List<String> list, String id) {
        if (list.contains(id) == false) {
            list.add(id);
            return true;
        }
        return false;

    }
}
