package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {

    @GetMapping("/admin")
    public String showIndexPageAdmin() {
        return "admin";
    }

    @GetMapping("/user")
    public String showIndexPageUser() {
        return "user";
    }
}