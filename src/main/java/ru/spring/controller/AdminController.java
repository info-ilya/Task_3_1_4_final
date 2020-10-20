package ru.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.service.UserService;
import ru.spring.model.User;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/admin")
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.findAllUsers());
        return "admin/admin";
    }

    @GetMapping("/admin/userinfo")
    public String showUserInfoPage() {
        return "admin/userinfo";
    }

    @GetMapping("/{name}")
    public String showSingleUserInfo(@PathVariable("name") String name, Model model) {
        model.addAttribute("user", userService.findByName(name));
        return "admin/userinfo";
    }

    @GetMapping("/del")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/admin";
    }

    @GetMapping("/edit")
    public String editUserPage(@RequestParam(name = "name") String name, Model model) {
        User user = userService.findByName(name);
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/new")
    public String showNewUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping("/admin/new")
    public String registerNewUser(@ModelAttribute("user") User user, Model model) {
        User existing = userService.findByName(user.getUserName());
        if (existing != null) {
            model.addAttribute("user", new User());
            model.addAttribute("registrationError", "Такой логин уже зарегистрирован.");
            return "admin/new";
        }
        userService.saveUser(user);
        return "redirect:/admin/admin";
    }

}
