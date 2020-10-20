package ru.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.spring.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(Long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
