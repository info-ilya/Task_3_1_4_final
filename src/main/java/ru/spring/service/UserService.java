package ru.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.spring.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String name);

    User findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
