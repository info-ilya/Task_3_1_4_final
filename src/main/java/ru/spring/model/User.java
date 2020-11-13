package ru.spring.model;

import lombok.*;

@Data
@NoArgsConstructor
public class User{

    private Long id;

    private Byte age;

    private String name;

    private String lastName;

    public User(Long id, String name, String lastName, Byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
}

