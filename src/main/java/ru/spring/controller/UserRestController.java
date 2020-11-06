package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.User;
import ru.spring.repository.RoleRepository;
import ru.spring.repository.UserRepository;
import ru.spring.service.UserService;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    // Получить user по id
    @GetMapping("/findone")
    public User findOne(Long id) {
        return userService.findById(id);
    }

    // Получить все user
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Получить все roles
//    @GetMapping("/admin")
//    public List getAllRoles() {
//        return roleRepository.findAll();
//    }

    // Создать нового user
    @PostMapping("/newuser")
    public User registerNewUser(@RequestBody User user) {
        return userService.save(user);
    }

    // Удалить user
    @DeleteMapping("/delete/{id1}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id1") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    // Обновить запись
    @PutMapping("/edituser/{id}")
    public User editUser(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user.getPassword().startsWith("$")) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setAge(user.getAge());
        user.setEmail(user.getEmail());
        user.setRoles(user.getRoles());

        return userService.save(user);
    }

}
