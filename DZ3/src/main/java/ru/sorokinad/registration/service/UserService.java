package ru.sorokinad.registration.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.registration.model.User;


@Service

public class UserService {

    public User createUser(String name, int age, String email) {
        return new User(name, age, email);
    }
}
