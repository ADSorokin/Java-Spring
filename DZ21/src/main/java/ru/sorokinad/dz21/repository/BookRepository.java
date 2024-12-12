package ru.sorokinad.dz21.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.sorokinad.dz21.model.Book;


import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> new Book(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getInt("year")
    );

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", bookRowMapper);
    }

    public Optional<Book> findById(Long id) {
        return jdbcTemplate.query(
                "SELECT * FROM books WHERE id = ?",
                bookRowMapper,
                id
        ).stream().findFirst();
    }

    public void save(Book book) {
        if (book.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO books (title, author, year) VALUES (?, ?, ?)",
                    book.getTitle(), book.getAuthor(), book.getYear()
            );
        } else {
            jdbcTemplate.update(
                    "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?",
                    book.getTitle(), book.getAuthor(), book.getYear(), book.getId()
            );
        }
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}
