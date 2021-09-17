package br.com.study.libraryapi.api.service;

import br.com.study.libraryapi.api.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BookService {
    Book save(Book any) ;

    Optional<Book> getById(Long id);

    void delete(Book book);

    Book update(Book book);

    Page<Book> find(Book any, Pageable any1);


    Optional<Book> getBookByIsbn(String s);
}
