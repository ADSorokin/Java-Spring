package ru.sorokinad.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.sorokinad.library.model.Book;
import ru.sorokinad.library.repository.BookRepository;

@SpringBootApplication
public class LibraryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryAppApplication.class, args);
        System.out.println("Приложение библиотеки запущено...");
    }

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("Война миров", "Герберт Уэлс", 2018));
            bookRepository.save(new Book("Война и Мир", "Л.Н Толстой", 2008));
            bookRepository.save(new Book("Spring Boot в действии", "Крейг Уоллс", 2016));
            System.out.println("В базу добавлены начальные книги!");
        };
    }
}
