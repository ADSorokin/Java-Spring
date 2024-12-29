package ru.sorokinad.dz10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinad.dz10.model.Book;
import ru.sorokinad.dz10.model.Reader;
import ru.sorokinad.dz10.model.ReaderBook;
import ru.sorokinad.dz10.repository.BookRepository;
import ru.sorokinad.dz10.repository.ReaderBookRepository;
import ru.sorokinad.dz10.repository.ReaderRepository;
import ru.sorokinad.dz10.service.ReaderBookService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ReaderBookServiceIntegrationTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderBookRepository readerBookRepository;

    @Autowired
    private ReaderBookService readerBookService;

    private Reader testReader;
    private Book testBook;

    @BeforeEach
    void setup() {
        // Создаем тестового читателя
        testReader = new Reader();
        testReader.setName("Test Reader");
        testReader.setEmail("testreader@example.com");
        readerRepository.save(testReader);

        // Создаем тестовую книгу
        testBook = new Book();
        testBook.setTitle("Test Book");
        testBook.setAuthor("Test Author");
        testBook.setPublisher("Test Publisher");
        testBook.setPublicationYear(2023);
        testBook.setTotalCopies(5);
        bookRepository.save(testBook);
    }

    @AfterEach
    void cleanup() {
        readerBookRepository.deleteAll();
        bookRepository.deleteAll();
        readerRepository.deleteAll();
    }

    @Test
    void testAssignBookToReader() {

        readerBookService.assignBookToReader(testReader.getId(), testBook.getId());


        List<ReaderBook> readerBooks = readerBookRepository.findAll();
        assertEquals(1, readerBooks.size());

        ReaderBook assignedReaderBook = readerBooks.get(0);
        assertEquals(testReader.getId(), assignedReaderBook.getReader().getId());
        assertEquals(testBook.getId(), assignedReaderBook.getBook().getId());
        assertNotNull(assignedReaderBook.getBorrowTimestamp());
    }

    @Test
    void testReturnBook() {

        readerBookService.assignBookToReader(testReader.getId(), testBook.getId());


        readerBookService.returnBook(testReader.getId(), testBook.getId());

        List<ReaderBook> readerBooks = readerBookRepository.findAll();
        assertEquals(0, readerBooks.size());
    }

    @Test
    void testGetBooksByReader() {

        readerBookService.assignBookToReader(testReader.getId(), testBook.getId());

        List<ReaderBook> booksByReader = readerBookService.getBooksByReader(testReader.getId());


        assertEquals(1, booksByReader.size());
        assertEquals(testBook.getId(), booksByReader.get(0).getBook().getId());
    }

    @Test
    void testGetReadersByBook() {

        readerBookService.assignBookToReader(testReader.getId(), testBook.getId());

        List<ReaderBook> readersByBook = readerBookService.getReadersByBook(testBook.getId());


        assertEquals(1, readersByBook.size());
        assertEquals(testReader.getId(), readersByBook.get(0).getReader().getId());
    }
}