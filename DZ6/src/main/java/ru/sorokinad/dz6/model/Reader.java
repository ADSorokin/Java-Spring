package ru.sorokinad.dz6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();


    public void addBook(Book book) {
        books.add(book);
        book.setReader(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setReader(null);
    }
}