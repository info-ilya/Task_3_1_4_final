package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.spring.error.UserNotFoundException;
import ru.spring.model.User;
import ru.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{user_id}")
    public User findOneUser(@PathVariable Long user_id) {
        if (!userService.isUserExist(userService.findById(user_id))) {
            throw new UserNotFoundException("User id not found - " + user_id);
        }
        return userService.findById(user_id);
    }

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

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam(name = "id1") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edituser")
    public void editUser(User user) {
        userService.updateUser(user);
    }

}
