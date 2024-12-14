package ru.sorokinad.studentApp.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.sorokinad.studentApp.model.Student;
import ru.sorokinad.studentApp.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init() {

        studentRepository.save(new Student("Вася", "Начертательная геометрия"));
        studentRepository.save(new Student("Вова", "Физика"));
        studentRepository.save(new Student("Оля", "Химия"));
        studentRepository.save(new Student("Галя", "Астрономия"));
        studentRepository.save(new Student("Петя", "Математика"));
    }
}
