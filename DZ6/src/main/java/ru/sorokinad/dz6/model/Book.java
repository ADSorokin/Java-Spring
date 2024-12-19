package ru.sorokinad.dz6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private int availableCopies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    @JsonIgnore // Чтобы избежать циклических ссылок при сериализации
    private Reader reader;
}