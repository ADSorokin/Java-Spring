package ru.sorokinad.dz6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinad.dz6.aop.TrackUserAction;
import ru.sorokinad.dz6.model.Reader;
import ru.sorokinad.dz6.repository.BookRepository;
import ru.sorokinad.dz6.repository.IssueRepository;
import ru.sorokinad.dz6.repository.ReaderRepository;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ReaderService {
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    private final IssueRepository issueRepository;

    public ReaderService(BookRepository bookRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    @TrackUserAction
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
    @TrackUserAction
    public Optional<Reader> getReaderById(Long id) {
        return readerRepository.findById(id);
    }
    @Transactional
    @TrackUserAction
    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }
 //   @Transactional
 //   @TrackUserAction
//    public void deleteReader(Long id) {
//        readerRepository.deleteById(id);
 //   }
    @Transactional
    @TrackUserAction
    public void deleteReaderAndUnassignBooks(Long readerId) {

        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new IllegalArgumentException("Reader with ID " + readerId + " not found."));

        reader.getBooks().forEach(book -> {
            book.setReader(null);

            bookRepository.save(book); // Сохраняем изменения
        });


        readerRepository.delete(reader);
    }
}
