package ru.sorokinad.dz7simple.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {

        return "error";
    }
}