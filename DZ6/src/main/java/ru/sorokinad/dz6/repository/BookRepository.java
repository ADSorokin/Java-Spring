package ru.sorokinad.dz6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz6.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
