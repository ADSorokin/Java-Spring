package ru.sorokinad.dz10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sorokinad.dz10.model.Book;
import ru.sorokinad.dz10.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Получение списка всех книг
     *
     * @return список книг
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Получить книгу по ID
     *
     * @param id ID книги
     * @return объект книги
     * @throws RuntimeException если книга не найдена
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга с ID " + id + " не найдена"));
    }

    /**
     * Сохранить новую книгу или обновить существующую
     *
     * @param book объект книги
     * @return сохранённая книга
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Удалить книгу по ID
     *
     * @param id ID книги
     */
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Книга с ID " + id + " не существует");
        }
        bookRepository.deleteById(id);
    }

    /**
     * Обновить данные о книге
     *
     * @param id          ID книги
     * @param updatedBook объект с новыми данными книги
     */
    public void updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);

        // Обновляем поля
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        existingBook.setTotalCopies(updatedBook.getTotalCopies());

        // Сохраняем изменения
        bookRepository.save(existingBook);
    }

    public int getAvailableBooks() {
        return 0;
    }
}



