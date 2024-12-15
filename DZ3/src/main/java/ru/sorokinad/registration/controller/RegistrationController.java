package ru.sorokinad.registration.controller;

import org.springframework.web.bind.annotation.*;
import ru.sorokinad.registration.model.User;
import ru.sorokinad.registration.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/user")
    public User registerUser(@RequestParam String name, @RequestParam int age, @RequestParam String email) {
        return registrationService.registerUser(name, age, email);
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return registrationService.getAllUsers();
    }

    @GetMapping("/users/sorted")
    public List<User> getSortedUsers() {
        return registrationService.processAndSortUsers();
    }


    @GetMapping("/users/filtered")
    public List<User> getFilteredUsers(@RequestParam int minAge) {
        return registrationService.filterUsersByAge(minAge);
    }


    @GetMapping("/users/average-age")
    public double getAverageAge() {
        return registrationService.calculateAverageAge();
    }
}

