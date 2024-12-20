package ru.sorokinad.dz7simple.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PrivateController {

    @GetMapping("/private-data")
    public String privateData(Model model) {
        model.addAttribute("message", "Это приватная страница (только для администраторов)");
        model.addAttribute("isPrivate", true);
        return "private-data";
    }
}
