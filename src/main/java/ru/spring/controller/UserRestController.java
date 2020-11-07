package ru.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
//    public User registerNewUser(@RequestBody User user) {
    public User registerNewUser(User user) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            user = mapper.readValue("/newuser", User.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return userService.save(user);
    }

    // Удалить user
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "id1") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    // Обновить запись
    @PutMapping("/edituser/")
    public void editUser(User user) {
        userService.updateUser(user);
    }

}
