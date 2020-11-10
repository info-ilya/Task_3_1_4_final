package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.repository.RoleRepository;
import ru.spring.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("usersList", userService.findAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/admin";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user/user";
    }

    @GetMapping({"/", "/login"})
    public String showMyLoginPage() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

//    @PostMapping("/newuser")
//    public String registerNewUser(@ModelAttribute("user") User user, Model model) {
//        User existing = userService.findByEmail(user.getEmail());
//        if (existing != null) {
//            model.addAttribute("user", new User());
//            model.addAttribute("registrationError", "Такой логин уже зарегистрирован.");
//            return "admin/newuserform";
//        }
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }

//    @GetMapping("/{name}")
//    public String showSingleUserInfo(@PathVariable("name") String name, Model model) {
//        model.addAttribute("usersList", userService.findAllUsers());
//        model.addAttribute("roles", roleRepository.findAll());
//        model.addAttribute("user", userService.findByEmail(name));
//        model.addAttribute("currentuser", name);
//        return "admin/userinfotable";
//    }
}
