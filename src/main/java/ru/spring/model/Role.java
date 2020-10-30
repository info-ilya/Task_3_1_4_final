package ru.spring.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

