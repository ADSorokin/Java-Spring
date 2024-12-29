package ru.sorokinad.dz11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sorokinad.dz11.model.Book;
import ru.sorokinad.dz11.model.Reader;
import ru.sorokinad.dz11.model.ReaderBook;
import ru.sorokinad.dz11.model.ReaderBookId;
import ru.sorokinad.dz11.repository.BookRepository;
import ru.sorokinad.dz11.repository.ReaderBookRepository;
import ru.sorokinad.dz11.repository.ReaderRepository;
import ru.sorokinad.dz11.service.ReaderBookService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReaderBookServiceTest {

    @Mock
    private ReaderBookRepository readerBookRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ReaderBookService readerBookService;

    private Reader testReader;
    private Book testBook;
    private ReaderBook testReaderBook;
    private ReaderBookId testReaderBookId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Инициализация тестовых данных
        testReader = new Reader();
        testReader.setId(1L);
        testReader.setName("Test Reader");

        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Test Book");

        testReaderBookId = new ReaderBookId(1L, 1L);

        testReaderBook = new ReaderBook();
        testReaderBook.setId(testReaderBookId);
        testReaderBook.setReader(testReader);
        testReaderBook.setBook(testBook);
        testReaderBook.setBorrowTimestamp(LocalDateTime.now());
    }

    @Test
    void getAllReaderBooks_ShouldReturnList() {
        // Arrange
        when(readerBookRepository.findAll()).thenReturn(Arrays.asList(testReaderBook));

        // Act
        List<ReaderBook> result = readerBookService.getAllReaderBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(readerBookRepository, times(1)).findAll();
    }

    @Test
    void assignBookToReader_ShouldAssignBook() {
        // Arrange
        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(readerBookRepository.existsById_ReaderIdAndId_BookId(1L, 1L)).thenReturn(false);
        when(readerBookRepository.save(any(ReaderBook.class))).thenReturn(testReaderBook);

        // Act
        readerBookService.assignBookToReader(1L, 1L);

        // Assert
        verify(readerRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(readerBookRepository, times(1)).existsById_ReaderIdAndId_BookId(1L, 1L);
        verify(readerBookRepository, times(1)).save(any(ReaderBook.class));
    }

    @Test
    void assignBookToReader_WhenReaderNotFound_ShouldThrowException() {
        // Arrange
        when(readerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerBookService.assignBookToReader(99L, 1L);
        });
        assertEquals("Читатель не найден", exception.getMessage());
        verify(bookRepository, never()).findById(any());
    }

    @Test
    void assignBookToReader_WhenBookNotFound_ShouldThrowException() {
        // Arrange
        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerBookService.assignBookToReader(1L, 99L);
        });
        assertEquals("Книга не найдена", exception.getMessage());
    }

    @Test
    void assignBookToReader_WhenAlreadyAssigned_ShouldThrowException() {
        // Arrange
        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(readerBookRepository.existsById_ReaderIdAndId_BookId(1L, 1L)).thenReturn(true);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerBookService.assignBookToReader(1L, 1L);
        });
        assertEquals("Эта книга уже выдана этому читателю", exception.getMessage());
        verify(readerBookRepository, never()).save(any());
    }

    @Test
    void returnBook_ShouldReturnBook() {
        // Arrange
        when(readerBookRepository.findById_ReaderIdAndId_BookId(1L, 1L))
                .thenReturn(Optional.of(testReaderBook));
        doNothing().when(readerBookRepository).delete(testReaderBook);

        // Act
        readerBookService.returnBook(1L, 1L);

        // Assert
        verify(readerBookRepository, times(1))
                .findById_ReaderIdAndId_BookId(1L, 1L);
        verify(readerBookRepository, times(1)).delete(testReaderBook);
    }

    @Test
    void returnBook_WhenNotFound_ShouldThrowException() {
        // Arrange
        when(readerBookRepository.findById_ReaderIdAndId_BookId(99L, 99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerBookService.returnBook(99L, 99L);
        });
        assertEquals("Запись о выдаче книги не найдена", exception.getMessage());
        verify(readerBookRepository, never()).delete(any());
    }

    @Test
    void getBooksByReader_ShouldReturnList() {
        // Arrange
        when(readerBookRepository.findByReader_Id(1L))
                .thenReturn(Arrays.asList(testReaderBook));

        // Act
        List<ReaderBook> result = readerBookService.getBooksByReader(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(readerBookRepository, times(1)).findByReader_Id(1L);
    }

    @Test
    void getReadersByBook_ShouldReturnList() {
        // Arrange
        when(readerBookRepository.findByBook_Id(1L))
                .thenReturn(Arrays.asList(testReaderBook));

        // Act
        List<ReaderBook> result = readerBookService.getReadersByBook(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(readerBookRepository, times(1)).findByBook_Id(1L);
    }
}
