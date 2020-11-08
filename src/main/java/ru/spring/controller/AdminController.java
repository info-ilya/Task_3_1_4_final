package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.repository.RoleRepository;
import ru.spring.service.UserService;
import ru.spring.model.User;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

//    @GetMapping("/findone")
//    @ResponseBody
//    public User findOne(Long id) {
//        return userService.findById(id);
//    }

    @GetMapping("/admin")
    public String allUsers(Model model) {
        model.addAttribute("usersList", userService.findAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/admin";
    }

//    @PostMapping("/edituser")
//    public String editUser(User user) {
//        userService.updateUser(user);
//        return "redirect:/admin";
//    }

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

//    @GetMapping("/delete")
//    public String deleteUser(@RequestParam(name = "id1") Long id) {
//        userService.deleteUserById(id);
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
