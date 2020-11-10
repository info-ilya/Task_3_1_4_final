package ru.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spring.model.User;
import ru.spring.repository.RoleRepository;
import ru.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getOneUser(@PathVariable("userId") Long userId) {
        if (!userService.isUserExist(userService.findById(userId))) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return userService.findById(userId);
    }

    @PostMapping(value = "/users")
    public User addNewUser(@RequestBody User user) {
        return userService.save(user);
    }


//    @RequestMapping(value = "/users",
//            method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    public @ResponseBody ResponseEntity<User> createEmployee( User user){
//        userService.save(user);
//        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
//    }

    //    @DeleteMapping("/users/{userId}")
    @RequestMapping(value = "/users/{id1}",
            produces = "application/json",
            method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id1") Long id1) {
        userService.deleteUserById(id1);
    }

    //    @PutMapping("/users")
    @RequestMapping(value = "/users",
            produces = "application/json",
            method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) {
        userService.updateUser(user);
    }

}
