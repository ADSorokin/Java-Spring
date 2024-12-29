package ru.sorokinad.dz11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz11.model.Reader;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    // Поиск читателей по имени
    List<Reader> findByName(String name);

    // Проверка по email
    Reader findByEmail(String email);

    Reader getReaderById(Long readerId);

}
