package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(1L, "War and Peace", "Leo Tolstoy", 1869));
        books.add(new Book(2L, "1984", "George Orwell", 1949));
    }

    public List<Book> findAll() {
        return books;
    }
}



