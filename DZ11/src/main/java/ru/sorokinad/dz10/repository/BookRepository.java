package ru.sorokinad.dz10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz10.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookById(Long bookId);


}
