package ru.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.spring.error.UserErrorResponse;
import ru.spring.error.UserNotFoundException;
import ru.spring.model.User;
import ru.spring.repository.RoleRepository;
import ru.spring.repository.UserRepository;
import ru.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Получить все user
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    // Получить user по id
    @GetMapping("/users/{user_id}")
    public User findOneUser(@PathVariable Long user_id) {
        if (!userService.isUserExist(userService.findById(user_id))) {
            throw new UserNotFoundException("User id not found - " + user_id);
        }
        return userService.findById(user_id);
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
    @PutMapping("/edituser")
    public void editUser(User user) {
        userService.updateUser(user);
    }

}
