package ru.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findRoleByName(String roleName);
}
