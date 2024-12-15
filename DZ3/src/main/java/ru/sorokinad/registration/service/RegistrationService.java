package ru.sorokinad.registration.service;


import org.springframework.stereotype.Service;
import ru.sorokinad.registration.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    private final UserService userService;
    private final DataProcessingService dataProcessingService;
    private final NotificationService notificationService;

    private final List<User> userList = new ArrayList<>();

    public RegistrationService(UserService userService, DataProcessingService dataProcessingService, NotificationService notificationService) {
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
        this.notificationService = notificationService;
    }

    public User registerUser(String name, int age, String email) {

        User user = userService.createUser(name, age, email);
        userList.add(user);


        notificationService.notify("Пользователь " + name + " был зарегистрирован.");
        return user;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<User> processAndSortUsers() {
        List<User> sortedUsers = dataProcessingService.sortUsersByAge(userList);
        notificationService.notify("Пользователи отсортированы по возрасту.");
        return sortedUsers;
    }

    public List<User> filterUsersByAge(int minAge) {
        List<User> filteredUsers = dataProcessingService.filterUsersByAge(userList, minAge);
        notificationService.notify("Пользователи отфильтрованы по возрасту > " + minAge);
        return filteredUsers;
    }

    public double calculateAverageAge() {
        double averageAge = dataProcessingService.calculateAverageAge(userList);
        notificationService.notify("Рассчитан средний возраст: " + averageAge);
        return averageAge;
    }


}
