package ru.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.spring.controller.UserRestController;
import ru.spring.model.User;

import java.util.List;

@SpringBootApplication(scanBasePackages = "ru.spring")
public class Task314Application {

    public static void main(String[] args) {
        //SpringApplication.run(Task314Application.class, args);

        UserRestController restController = new UserRestController();

        List<User> usersList = restController.getAllUsers();
        System.out.println(usersList);
        System.out.println(restController.getCookie());

        User user = new User(3L, "James", "Brown", (byte) 111);
        restController.addNewUser(user);
        System.out.println(restController.getCode());

        user.setName("Thomas");
        user.setLastName("Shelby");
        restController.editUser(user);
        System.out.println(restController.getCode());

        restController.deleteUser(user);
        System.out.println(restController.getCode());

    }

}
