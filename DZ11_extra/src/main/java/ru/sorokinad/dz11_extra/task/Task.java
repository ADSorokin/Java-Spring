package ru.sorokinad.dz11_extra.task;


import jakarta.persistence.*;


@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean urgent;

    public Task(String title, String description, boolean urgent) {
        this.title = title;
        this.description = description;
        this.urgent = urgent;
    }

    public Task(Long id, String title, String description, boolean urgent) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urgent = urgent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public Task() {
    }
}
