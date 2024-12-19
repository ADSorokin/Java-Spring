package ru.sorokinad.dz6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sorokinad.dz6.model.Reader;
import ru.sorokinad.dz6.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;



    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteReader(Long id) {
        readerRepository.deleteById(id);
    }
}
