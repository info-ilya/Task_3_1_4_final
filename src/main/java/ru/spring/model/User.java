package ru.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "age")
    private Byte age;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastName;

    public User(Byte age, String name, String lastName) {
        this.age = age;
        this.name = name;
        this.lastName = lastName;
    }
}

