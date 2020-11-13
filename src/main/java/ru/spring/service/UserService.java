package ru.spring.service;

import ru.spring.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User save(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    boolean isUserExists(User user);
}
