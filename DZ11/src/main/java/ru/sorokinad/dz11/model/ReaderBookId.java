package ru.sorokinad.dz11.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderBookId implements Serializable {
    @Column(name = "reader_id")
    private Long readerId;

    @Column(name = "book_id")
    private Long bookId;
}