package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Book;
import ru.sorokinad.dz4.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }


    public void delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Книги с id " + id + "не существует");
        }
    }
}



