package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public String showAdminPage(Model model) {
        model.addAttribute("usersList", userService.findAllUsers());
        return "/admin";
    }
}
