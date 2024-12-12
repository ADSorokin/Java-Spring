package ru.sorokinad.dz21.service;

import ru.sorokinad.dz21.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void save(Book book);
    void deleteById(Long id);
}
