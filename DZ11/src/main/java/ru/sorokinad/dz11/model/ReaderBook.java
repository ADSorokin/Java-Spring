package ru.sorokinad.dz11.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reader_books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderBook {
    @EmbeddedId
    private  ReaderBookId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("readerId")
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrow_timestamp", nullable = false)
    private LocalDateTime borrowTimestamp;

    public ReaderBook( Reader reader, Book book, LocalDateTime borrowTimestamp) {

        this.reader = reader;
        this.book = book;
        this.borrowTimestamp = borrowTimestamp;
    }
}
