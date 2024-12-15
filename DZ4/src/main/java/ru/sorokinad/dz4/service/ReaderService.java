package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Reader;
import ru.sorokinad.dz4.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }


    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> findById(Long id) {
        return readerRepository.findById(id);
    }


    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }


    public void delete(Long id) {
        if (readerRepository.existsById(id)) {
            readerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Читалка с id " + id + " не существует");
        }
    }
}