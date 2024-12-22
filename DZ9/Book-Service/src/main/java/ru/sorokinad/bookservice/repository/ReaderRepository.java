package ru.sorokinad.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.bookservice.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}