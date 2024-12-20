package ru.sorokinad.resourceserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataController {

    @GetMapping("/public-data")
    public String publicData() {
        return "This is public data for authenticated users.";
    }

    @GetMapping("/private-data")
    public String privateData() {
        return "This is private data for admins.";
    }
}
