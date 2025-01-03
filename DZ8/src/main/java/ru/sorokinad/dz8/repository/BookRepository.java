package ru.sorokinad.dz8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz8.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
