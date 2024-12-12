package ru.sorokinad.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sorokinad.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}