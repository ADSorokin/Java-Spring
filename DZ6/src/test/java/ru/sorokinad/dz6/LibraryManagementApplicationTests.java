package ru.sorokinad.dz6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.sorokinad.dz6.model.Book;
import ru.sorokinad.dz6.model.Reader;
import ru.sorokinad.dz6.repository.BookRepository;
import ru.sorokinad.dz6.repository.ReaderRepository;


import java.time.Duration;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.internal.matchers.NotNull.NOT_NULL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryManagementApplicationTests {

    @LocalServerPort
    private int port;

    private WebTestClient webTestClient;

    @Autowired
    private BookRepository bookRepository;

    private ReaderRepository readerRepository;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .responseTimeout(Duration.ofSeconds(10))
                .build();

        // Очистка и подготовка тестовых данных
        bookRepository.deleteAll();
    }

    @Test
    void testGetAllBooks() {
        // Подготовка тестовых данных
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        bookRepository.save(book);

        // Выполнение теста
        webTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Book.class)
                .hasSize(1);
    }

    @Test
    void testCreateBook() {
        Book newBook = new Book();
        newBook.setTitle("New Book");
        newBook.setAuthor("New Author");

        webTestClient.post()
                .uri("/api/books")
                .bodyValue(newBook)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(book -> {
                    assert book.getId() != null;
                    assert book.getTitle().equals("New Book");
                });
    }

    @Test
    void testGetBookById() {
        // Создаем книгу для теста
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        Book savedBook = bookRepository.save(book);

        webTestClient.get()
                .uri("/api/books/" + savedBook.getId())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(retrievedBook -> {
                    assert retrievedBook.getId().equals(savedBook.getId());
                    assert retrievedBook.getTitle().equals("Test Book");
                });
    }

    @Test
    void testUpdateBook() {
        // Создаем книгу для теста
        Book book = new Book();
        book.setTitle("Original Title");
        book.setAuthor("Original Author");
        Book savedBook = bookRepository.save(book);

        // Обновляем данные
        savedBook.setTitle("Updated Title");

        webTestClient.put()
                .uri("/api/books/" + savedBook.getId())
                .bodyValue(savedBook)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(updatedBook -> {
                    assert updatedBook.getTitle().equals("Updated Title");
                });
    }

    @Test
    void testDeleteBook() {
        // Создаем книгу для теста
        Book book = new Book();
        book.setTitle("Book to Delete");
        book.setAuthor("Author");
        Book savedBook = bookRepository.save(book);

        webTestClient.delete()
                .uri("/api/books/" + savedBook.getId())
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Проверяем, что книга удалена
        webTestClient.get()
                .uri("/api/books/" + savedBook.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void test_assignBookToReader() {
        Book existingBook = bookRepository.findAll().get(0);
        Reader reader = new Reader("John Doe", "john@example.com");

        readerRepository.save(reader);

        webTestClient.put()
                .uri("/api/books/" + existingBook.getId() + "/assign/" + reader.getId())
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .value(updatedBook -> {
                    assert(updatedBook.getReader()).equals(NOT_NULL);
                    assert(updatedBook.getReader().getId()).equals(reader.getId());
                });

}


    @Test
    void test_addReader() {
        Reader newReader = new Reader();
        newReader.setName("John Smith");
        newReader.setEmail("johnsmith@example.com");

        webTestClient.post()
                .uri("/api/readers")
                .bodyValue(newReader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .value(savedReader -> {
                    assert(savedReader.getId()).equals(NOT_NULL);
                    assert(savedReader.getName()).equals("John Smith");
                });
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