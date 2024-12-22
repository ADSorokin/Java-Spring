package ru.sorokinad.readerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.readerservice.model.Reader;


@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}