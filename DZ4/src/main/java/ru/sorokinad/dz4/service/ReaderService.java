package ru.sorokinad.dz4.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.dz4.model.Reader;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderService {

    private final List<Reader> readers = new ArrayList<>();

    public ReaderService() {
        readers.add(new Reader(1L, "Ivan Ivanov"));
        readers.add(new Reader(2L, "Anna Petrovna"));
    }

    public List<Reader> getAllReaders() {
        return readers;
    }
}