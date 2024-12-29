package ru.sorokinad.dz11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sorokinad.dz11.model.Book;
import ru.sorokinad.dz11.model.Reader;
import ru.sorokinad.dz11.repository.BookRepository;
import ru.sorokinad.dz11.repository.ReaderRepository;
import ru.sorokinad.dz11.service.ReaderService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReaderServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderService readerService;

    private Reader testReader;
    private Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Инициализация тестового читателя
        testReader = new Reader();
        testReader.setId(1L);
        testReader.setName("John Doe");
        testReader.setEmail("john@example.com");

        // Инициализация тестовой книги
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Test Book");
    }

    @Test
    void getAllReaders_ShouldReturnListOfReaders() {
        // Arrange
        when(readerRepository.findAll()).thenReturn(Arrays.asList(testReader));

        // Act
        List<Reader> result = readerService.getAllReaders();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testReader.getName(), result.get(0).getName());
        verify(readerRepository, times(1)).findAll();
    }

    @Test
    void getReaderById_WhenReaderExists_ShouldReturnReader() {
        // Arrange
        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));

        // Act
        Reader result = readerService.getReaderById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(testReader.getName(), result.getName());
        assertEquals(testReader.getEmail(), result.getEmail());
        verify(readerRepository, times(1)).findById(1L);
    }

    @Test
    void getReaderById_WhenReaderNotFound_ShouldThrowException() {
        // Arrange
        when(readerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerService.getReaderById(99L);
        });
        assertEquals("Читатель не найден", exception.getMessage());
        verify(readerRepository, times(1)).findById(99L);
    }

    @Test
    void saveReader_ShouldSaveReader() {
        // Arrange
        when(readerRepository.save(any(Reader.class))).thenReturn(testReader);

        // Act
        readerService.saveReader(testReader);

        // Assert
        verify(readerRepository, times(1)).save(testReader);
    }

    @Test
    void updateReader_WhenReaderExists_ShouldUpdateReader() {
        // Arrange
        Reader updatedReader = new Reader();
        updatedReader.setName("Updated Name");
        updatedReader.setEmail("updated@example.com");

        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));
        when(readerRepository.save(any(Reader.class))).thenReturn(testReader);

        // Act
        readerService.updateReader(1L, updatedReader);

        // Assert
        verify(readerRepository, times(1)).findById(1L);
        verify(readerRepository, times(1)).save(any(Reader.class));
        assertEquals("Updated Name", testReader.getName());
        assertEquals("updated@example.com", testReader.getEmail());
    }

    @Test
    void updateReader_WhenReaderNotFound_ShouldThrowException() {
        // Arrange
        Reader updatedReader = new Reader();
        when(readerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerService.updateReader(99L, updatedReader);
        });
        assertEquals("Читатель не найден", exception.getMessage());
        verify(readerRepository, times(1)).findById(99L);
        verify(readerRepository, never()).save(any(Reader.class));
    }

    @Test
    void deleteReader_WhenReaderExists_ShouldDeleteReader() {
        // Arrange
        when(readerRepository.findById(1L)).thenReturn(Optional.of(testReader));
        doNothing().when(readerRepository).delete(testReader);

        // Act
        readerService.deleteReader(1L);

        // Assert
        verify(readerRepository, times(1)).findById(1L);
        verify(readerRepository, times(1)).delete(testReader);
    }

    @Test
    void deleteReader_WhenReaderNotFound_ShouldThrowException() {
        // Arrange
        when(readerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerService.deleteReader(99L);
        });
        assertEquals("Читатель не найден", exception.getMessage());
        verify(readerRepository, times(1)).findById(99L);
        verify(readerRepository, never()).delete(any(Reader.class));
    }

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        // Act
        Book result = readerService.getBookById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(testBook.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_WhenBookNotFound_ShouldThrowException() {
        // Arrange
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            readerService.getBookById(99L);
        });
        assertEquals("Читатель не найден", exception.getMessage());
        verify(bookRepository, times(1)).findById(99L);
    }
}

