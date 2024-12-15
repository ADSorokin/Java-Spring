package ru.sorokinad.dz4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz4.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
