package ru.sorokinad.dz7simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PublicController {

    @GetMapping("/public-data")
    public String publicData(Model model) {
        model.addAttribute("message", "Это публичная страница (для всех аутентифицированных пользователей)");
        model.addAttribute("isPrivate", false);
        return "public-data";
    }
}
