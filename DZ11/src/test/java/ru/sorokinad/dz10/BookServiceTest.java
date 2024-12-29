package ru.sorokinad.dz10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sorokinad.dz10.model.Book;
import ru.sorokinad.dz10.repository.BookRepository;
import ru.sorokinad.dz10.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Инициализация тестовой книги
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("Test Book");
        testBook.setAuthor("Author Name");
        testBook.setPublisher("Publisher Name");
        testBook.setPublicationYear(2022);
        testBook.setTotalCopies(5);
    }

    @Test
    void getAllBooks_ShouldReturnListOfBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook));

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testBook.getTitle(), result.get(0).getTitle());

    }

 

    @Test
    void getBookById_WhenBookExists_ShouldReturnBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        // Act
        Book result = bookService.getBookById(1L);

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
            bookService.getBookById(99L);
        });
        assertEquals("Книга с ID 99 не найдена", exception.getMessage());
        verify(bookRepository, times(1)).findById(99L);
    }

    @Test
    void saveBook_ShouldSaveBook() {
        // Arrange
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        // Act
        Book result = bookService.saveBook(testBook);

        // Assert
        assertNotNull(result);
        assertEquals(testBook.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void deleteBook_WhenBookExists_ShouldDeleteBook() {
        // Arrange
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        // Act
        bookService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteBook_WhenBookNotFound_ShouldThrowException() {
        // Arrange
        when(bookRepository.existsById(99L)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.deleteBook(99L);
        });
        assertEquals("Книга с ID 99 не существует", exception.getMessage());
        verify(bookRepository, times(1)).existsById(99L);
        verify(bookRepository, never()).deleteById(any(Long.class));
    }

    @Test
    void updateBook_WhenBookExists_ShouldUpdateBook() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book updatedBook = new Book();
        updatedBook.setTitle("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPublisher("Updated Publisher");
        updatedBook.setPublicationYear(2023);
        updatedBook.setTotalCopies(10);

        // Act
        bookService.updateBook(1L, updatedBook);

        // Assert
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(testBook);
        assertEquals("Updated Title", testBook.getTitle());
        assertEquals("Updated Author", testBook.getAuthor());
        assertEquals("Updated Publisher", testBook.getPublisher());
        assertEquals(2023, testBook.getPublicationYear());
        assertEquals(10, testBook.getTotalCopies());
    }

    @Test
    void updateBook_WhenBookNotFound_ShouldThrowException() {
        // Arrange
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.updateBook(99L, testBook);
        });
        assertEquals("Книга с ID 99 не найдена", exception.getMessage());
        verify(bookRepository, times(1)).findById(99L);
        verify(bookRepository, never()).save(any(Book.class));
    }
}