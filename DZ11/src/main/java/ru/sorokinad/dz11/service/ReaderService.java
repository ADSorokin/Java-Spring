package ru.sorokinad.dz11.service;


import org.springframework.stereotype.Service;
import ru.sorokinad.dz11.model.Book;
import ru.sorokinad.dz11.model.Reader;
import ru.sorokinad.dz11.repository.BookRepository;
import ru.sorokinad.dz11.repository.ReaderRepository;


import java.util.List;

@Service
public class ReaderService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public ReaderService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public static void saveReder(Reader reader) {
    }

    // Работа с читателями


    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReaderById(Long readerId) {
        return readerRepository.findById(readerId).orElseThrow(() -> new RuntimeException("Читатель не найден"));
    }

    public void saveReader(Reader reader) {
        readerRepository.save(reader);
    }

    // Связь читатель-книга
    public void updateReader(Long id, Reader updatedReader) {
        Reader existingReader = getReaderById(id);

        // Обновляем поля
        existingReader.setName(updatedReader.getName());
        existingReader.setEmail(updatedReader.getEmail());


        // Сохраняем изменения
        readerRepository.save(existingReader);
    }




    public void deleteReader(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Читатель не найден"));
        readerRepository.delete(reader);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Читатель не найден"));
    }


}
