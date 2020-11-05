package ru.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

