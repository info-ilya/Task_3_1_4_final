package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.User;
import ru.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getOneUser(@PathVariable Long userId) {
        if (!userService.isUserExist(userService.findById(userId))) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        user.setId(0L);
        return userService.save(user);
    }

    //    @DeleteMapping("/users/{userId}")
    @RequestMapping(value = "/users/{userId}",
            produces = "application/json",
            method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return "Users delete";
    }

    //    @PutMapping("/users")
    @RequestMapping(value = "/users",
            produces = "application/json",
            method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) {
        userService.updateUser(user);
    }

}
