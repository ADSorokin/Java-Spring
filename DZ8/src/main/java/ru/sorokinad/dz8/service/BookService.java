package ru.sorokinad.dz8.service;


import jakarta.persistence.OptimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinad.dz8.aop.TrackUserAction;
import ru.sorokinad.dz8.model.Book;
import ru.sorokinad.dz8.model.Reader;
import ru.sorokinad.dz8.repository.BookRepository;
import ru.sorokinad.dz8.repository.ReaderRepository;


import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class BookService {



    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }


    @TrackUserAction
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @TrackUserAction
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    @TrackUserAction
    public Book saveBook(Book book) {
        try {

            return bookRepository.save(book);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException(
                    "Record was updated by another transaction", e);
        }
    }
    @Transactional
    @TrackUserAction
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @TrackUserAction
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();


            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());

            try {
                return bookRepository.save(book);
            } catch (OptimisticLockException e) {
                throw new IllegalStateException("Conflict detected: another transaction modified the Book record", e);
            }
        } else {
            throw new IllegalArgumentException("Book with id " + id + " not found.");
        }
    }
    @Transactional
    @TrackUserAction
    public void registerReaderWithBooks(Reader reader, List<Book> books) {
        // Сохраняем читателя
        Reader savedReader = readerRepository.save(reader);

        // Привязываем книги к читателю
        books.forEach(book -> {
            // Проверяем, что книга не привязана к другому читателю
            if (book.getReader() != null) {
                throw new IllegalStateException("Book with id " + book.getId() + " is already assigned to a reader.");
            }
            book.setReader(savedReader);
            bookRepository.save(book);
        });
    }
}