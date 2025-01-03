package ru.sorokinad.dz6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();




    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Reader() {
    }


    public Reader(Long id, String name, String email, List<Book> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public Reader( String name, String email) {

        this.name = name;
        this.email = email;

    }

    public Long getId() {
        return id;
    }



    public List<Book> getBooks() {
        return books;
    }





}