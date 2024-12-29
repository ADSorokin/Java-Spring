package ru.sorokinad.dz10.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;



    public Reader(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Reader() {

    }

    public Long getId() {
        return id;
    }

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





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader)) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}