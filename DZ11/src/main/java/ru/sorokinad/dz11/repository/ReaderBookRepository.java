package ru.sorokinad.dz11.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz11.model.ReaderBook;
import ru.sorokinad.dz11.model.ReaderBookId;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderBookRepository extends JpaRepository<ReaderBook, ReaderBookId> {
    List<ReaderBook> findByReader_Id(Long readerId);

    List<ReaderBook> findByBook_Id(Long bookId);

    Optional<ReaderBook> findById_ReaderIdAndId_BookId(Long readerId, Long bookId);

    boolean existsById_ReaderIdAndId_BookId(Long readerId, Long bookId);
}
