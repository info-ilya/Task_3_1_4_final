package ru.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/user")
    public String showUserMainPage() {
        return "user/user";
    }
}
