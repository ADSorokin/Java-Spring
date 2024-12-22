package ru.sorokinad.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.bookservice.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
