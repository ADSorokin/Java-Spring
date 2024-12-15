package ru.sorokinad.registration.service;

import org.springframework.stereotype.Service;
import ru.sorokinad.registration.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    public List<User> sortUsersByAge(List<User> users) {
        return users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int minAge) {
        return users.stream().filter(user -> user.getAge() > minAge).collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        OptionalDouble averageAge = users.stream().mapToInt(User::getAge).average();

        return averageAge.orElse(0.0); // Если если ничего нет то .0
    }
}
