package ru.sorokinad.dz7simple.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Authentication authentication) {

        if (authentication != null &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/private-data";
        }
        return "redirect:/public-data";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
