package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.spring.model.User;
import ru.spring.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    private final String url = "http://91.241.64.178:7081/api/users";

    private String cookie;

    public List<User> getAllUsers() {
         RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers;
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {
                });
        headers = responseEntity.getHeaders();
        setCookie(headers.getFirst("Cookie"));
        List<User> users = responseEntity.getBody();
        return users;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    //    @GetMapping("/users/{id}")
//    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
//        User user = userService.findById(id);
//        if (user == null) {
//            return new ResponseEntity<>(new RuntimeException("Product with id " + id + " not found"), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        if (userService.isUserExists(user)) {
            return new ResponseEntity<>(new RuntimeException("Unable to create. A Product with name " +
                    user.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(new RuntimeException("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        User userExists = userService.findById(user.getId());
        if (userExists == null) {
            return new ResponseEntity<>(new RuntimeException("Unable to upate. Product with id " + user.getId() + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
