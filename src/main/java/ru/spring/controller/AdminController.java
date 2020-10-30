package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.Role;
import ru.spring.repository.RoleRepository;
import ru.spring.service.UserService;
import ru.spring.model.User;

import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


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
        model.addAttribute("user", userService.findByEmail(name));
        return "admin/userinfo";
    }

    @GetMapping("/del")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/admin";
    }

    @GetMapping("/edit")
    public String editUserPage(@RequestParam(name = "name") String name, Model model) {
        User user = userService.findByEmail(name);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam("roles") Set<Role> roles) {
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin/admin";
    }

    @GetMapping("/admin/new")
    public String showNewUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/new";
    }

    @PostMapping("/admin/new")
    public String registerNewUser(@ModelAttribute("user") User user,
                                  @RequestParam("roles") Set<Role> roles,
                                  Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            model.addAttribute("user", new User());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("registrationError", "Такой логин уже зарегистрирован.");
            return "admin/new";
        }

        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin/admin";
    }

}
