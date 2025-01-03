package ru.sorokinad.dz8.conroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.sorokinad.dz8.model.Book;
import ru.sorokinad.dz8.model.Reader;
import ru.sorokinad.dz8.service.BookService;
import ru.sorokinad.dz8.service.ReaderService;

import java.util.List;
import java.util.Optional;


@Tag(name = "Books", description = "API для управления книгами")
//@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "API для управления книгами")
//@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReaderService readerService;

    public BookController(BookService bookService, ReaderService readerService) {
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @Operation(summary = "Получить список всех книг")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(summary = "Получить книгу по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Transactional
    @Operation(summary = "Добавить новую книгу")
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }
    @Transactional
    @Operation(summary = "Назначить книгу читателю")
    @PutMapping("/{bookId}/assign/{readerId}")
    public ResponseEntity<Book> assignBookToReader(
            @PathVariable Long bookId,
            @PathVariable Long readerId) {
        Optional<Book> bookOptional = bookService.getBookById(bookId);
        Optional<Reader> readerOptional = readerService.getReaderById(readerId);

        if (bookOptional.isPresent() && readerOptional.isPresent()) {
            Book book = bookOptional.get();
            Reader reader = readerOptional.get();


            if (book.getReader() != null && !book.getReader().getId().equals(readerId)) {
                return ResponseEntity.badRequest().build();
            }

            reader.addBook(book);
            return ResponseEntity.ok(bookService.saveBook(book));
        }
        return ResponseEntity.notFound().build();
    }
    @Transactional
    @Operation(summary = "Удалить книгу")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}