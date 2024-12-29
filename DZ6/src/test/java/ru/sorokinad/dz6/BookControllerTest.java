package ru.sorokinad.dz6;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.sorokinad.dz6.conroller.BookController;
import ru.sorokinad.dz6.model.Book;
import ru.sorokinad.dz6.model.Reader;
import ru.sorokinad.dz6.repository.ReaderRepository;
import ru.sorokinad.dz6.service.BookService;
import ru.sorokinad.dz6.service.ReaderService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@WebFluxTest(BookController.class) // Ограничиваем контекст тестирования только контроллером
class BookControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @MockitoBean
    @Autowired
    private ReaderRepository readerRepository;
    @MockitoBean
    @Autowired
    private BookService bookService; // Мокаем сервис

    @MockitoBean
    @Autowired
    private ReaderService readerService; // Мокаем сервис
    @Test
    void test_getAllBooks() {
        // Мокируем возвращаемый результат
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", "Author 1", "Publisher 1", 2023, 3),
                new Book(2L, "Book 2", "Author 2", "Publisher 2", 2022, 2)
        );

        given(bookService.getAllBooks()).willReturn(books);

        // Выполняем запрос к контроллеру
        webTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Book.class)
                .value(returnedBooks -> {
                    assert returnedBooks.size() == 2;
                    assert returnedBooks.get(0).getTitle().equals("Book 1");
                });

        // Проверяем, что метод сервиса вызван 1 раз
        verify(bookService, Mockito.times(1)).getAllBooks();
    }

    @Test
    void test_getBookById() {
        Book book = new Book(1L, "Book 1", "Author 1", "Publisher 1", 2023, 3);

        // Мокируем `bookService.getBookById`
        given(bookService.getBookById(1L)).willReturn(Optional.of(book));

        webTestClient.get()
                .uri("/api/books/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .value(returnedBook -> {
                    assert returnedBook.getTitle().equals("Book 1");
                    assert returnedBook.getId().equals(1L);
                });

        // Проверяем, что метод был вызван 1 раз с передачей ID=1
        verify(bookService, Mockito.times(1)).getBookById(1L);
    }

    @Test
    void test_createBook() {
        Book newBook = new Book(null, "New Book", "New Author", "New Publisher", 2023, 5);
        Book savedBook = new Book(1L, "New Book", "New Author", "New Publisher", 2023, 5);

        // Мокируем `bookService.saveBook`
        given(bookService.saveBook(any(Book.class))).willReturn(savedBook);

        webTestClient.post()
                .uri("/api/books")
                .bodyValue(newBook)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(returnedBook -> {
                    assert returnedBook.getId().equals(1L);
                    assert returnedBook.getTitle().equals("New Book");
                });

        // Проверяем, что метод сервиса был вызван 1 раз с передачей объекта-книги
        verify(bookService, Mockito.times(1)).saveBook(newBook);
    }

    @Test
    void test_updateBook() {
        Book updatedBook = new Book(1L, "Updated Book", "Updated Author", "Publisher", 2023, 4);

        // Мокируем `bookService.saveBook`
        given(bookService.saveBook(eq(updatedBook))).willReturn(updatedBook);

        webTestClient.put()
                .uri("/api/books/1")
                .bodyValue(updatedBook)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(returnedBook -> {
                    assert returnedBook.getTitle().equals("Updated Book");
                    assert returnedBook.getId().equals(1L);
                });

        // Проверяем, что метод `saveBook` был вызван с корректными данными
        verify(bookService, Mockito.times(1)).saveBook(updatedBook);
    }




    @Test
    void test_deleteBook() {
        // Проверяем вызов метода `deleteBook`
        Mockito.doNothing().when(bookService).deleteBook(1L);

        webTestClient.delete()
                .uri("/api/books/1")
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Проверяем, что метод `deleteBook` был вызван 1 раз
        verify(bookService, Mockito.times(1)).deleteBook(1L);
    }

    @Test
    void test_deleteReader() {
        // Создаем читателя
        Reader reader = new Reader(null, "Delete Me", "delete@example.com", null);
        Reader savedReader = readerRepository.save(reader);

        // Отправляем DELETE запрос
        webTestClient.delete()
                .uri("/api/readers/" + savedReader.getId())
                .exchange()
                .expectStatus().isOk();

        // Проверяем, что читателя нет в базе
        assertThat(readerRepository.findById(savedReader.getId())).isEmpty();
    }
}