package ru.sorokinad.readerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.readerservice.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}