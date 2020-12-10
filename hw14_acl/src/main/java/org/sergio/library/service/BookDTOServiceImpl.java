package org.sergio.library.service;

import lombok.extern.slf4j.Slf4j;
import org.sergio.library.domain.Author;
import org.sergio.library.domain.Book;
import org.sergio.library.domain.Genre;
import org.sergio.library.dto.AuthorDTO;
import org.sergio.library.dto.BookDTO;
import org.sergio.library.dto.GenreDTO;
import org.sergio.library.repository.AuthorRepo;
import org.sergio.library.repository.BookRepo;
import org.sergio.library.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class BookDTOServiceImpl implements BookDTOService {
    @Autowired
    private BookRepo repo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Override
    public Optional<BookDTO> createBookDTO(String name) {
        BookDTO bookDTO = null;
        Book book = repo.findByName(name);

        if (book != null) {
            bookDTO = new BookDTO();
            List<GenreDTO> genresDTO = fillGenres(book);
            bookDTO.setGenres(genresDTO);
            List<AuthorDTO> authorDTO = fillAuthors(book);
            bookDTO.setAuthors(authorDTO);
        }
        return Optional.ofNullable(bookDTO);
    }

    private List<GenreDTO> fillGenres(Book book) {
        List<GenreDTO> list = new ArrayList<>();
        if (book == null) return list;
        Iterable<Genre> iter = genreRepo.findAllById(book.getGenres_ids());
        List<Genre> lst = StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
        lst.forEach(x -> {
            GenreDTO dto = new GenreDTO();
            dto.setId(x.getId());
            dto.setName(x.getName());
            list.add(dto);
        });
        return list;
    }

    private List<AuthorDTO> fillAuthors(Book book) {
        List<AuthorDTO> list = new ArrayList<>();
        if (book == null) return list;
        Iterable<Author> iter = authorRepo.findAllById(book.getAuthors_ids());
        List<Author> lst = StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());
        lst.forEach(x -> {
            AuthorDTO dto = new AuthorDTO();
            dto.setId(x.getId());
            dto.setName(x.getName());
            dto.setSurName(x.getSurName());
            list.add(dto);
        });
        return list;
    }

/*
    private <T, U> List<T> fillDTO(Book book, MongoRepository<U, String> rep, List<String> str) {
        Class<T> clazzOfT = null;
        Class<U> clazzOfU = null;
        List<T> list = new ArrayList<>();
        if (book == null) return list;
        Iterable<U> iter = rep.findAllById(str);
        List<U> lst = StreamSupport.stream(iter.spliterator(), false).collect(Collectors.toList());

        lst.forEach(x -> {
            T dto = null;
            try {
                Method getIDofX = clazzOfU.getMethod("getId");
                Method getNameofX = clazzOfU.getMethod("getName");

                dto = clazzOfT.getDeclaredConstructor().newInstance();
                Method setId = clazzOfT.getMethod("setId", String.class);
                Method setName = clazzOfT.getMethod("setName", String.class);
                setId.invoke(dto, getIDofX.invoke(x));
                setName.invoke(dto, getNameofX.invoke(x));
                list.add(dto);

            } catch (InstantiationException e) {
               log.error(e.toString());
            } catch (InvocationTargetException e) {
                log.error(e.toString());
            } catch (NoSuchMethodException e) {
                log.error(e.toString());
            } catch (IllegalAccessException e) {
                log.error(e.toString());
            }
        });
        return list;
    }
*/
}