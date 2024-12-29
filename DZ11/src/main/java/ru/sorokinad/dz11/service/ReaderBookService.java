package ru.sorokinad.dz11.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinad.dz11.model.Book;
import ru.sorokinad.dz11.model.Reader;
import ru.sorokinad.dz11.model.ReaderBook;
import ru.sorokinad.dz11.model.ReaderBookId;
import ru.sorokinad.dz11.repository.BookRepository;
import ru.sorokinad.dz11.repository.ReaderBookRepository;
import ru.sorokinad.dz11.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;




@Service
@RequiredArgsConstructor
public class ReaderBookService {
    private final ReaderBookRepository readerBookRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public List<ReaderBook> getAllReaderBooks() {
        return readerBookRepository.findAll();
    }

    @Transactional
    public void assignBookToReader(Long readerId, Long bookId) {
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Читатель не найден"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));

        if (readerBookRepository.existsById_ReaderIdAndId_BookId(readerId, bookId)) {
            throw new RuntimeException("Эта книга уже выдана этому читателю");
        }

        ReaderBookId id = new ReaderBookId(readerId, bookId);
        ReaderBook readerBook = new ReaderBook();
        readerBook.setId(id);
        readerBook.setReader(reader);
        readerBook.setBook(book);
        readerBook.setBorrowTimestamp(LocalDateTime.now());

        readerBookRepository.save(readerBook);
    }

    @Transactional
    public void returnBook(Long readerId, Long bookId) {
        ReaderBook readerBook = readerBookRepository
                .findById_ReaderIdAndId_BookId(readerId, bookId)
                .orElseThrow(() -> new RuntimeException("Запись о выдаче книги не найдена"));

        readerBookRepository.delete(readerBook);
    }

    public List<ReaderBook> getBooksByReader(Long readerId) {
        return readerBookRepository.findByReader_Id(readerId);
    }

    public List<ReaderBook> getReadersByBook(Long bookId) {
        return readerBookRepository.findByBook_Id(bookId);
    }
}
