package ru.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.model.Role;
import ru.spring.model.User;
import ru.spring.repository.RoleRepository;
import ru.spring.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByUserName(name);
    }

    @Override
    public void saveUser(User theUser) {
        User user = new User();
        user.setUserName(theUser.getUserName());
        user.setPassword(passwordEncoder.encode(theUser.getPassword()));
        user.setFirstName(theUser.getFirstName());
        user.setLastName(theUser.getLastName());
        user.setEmail(theUser.getEmail());
        user.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User theUser) {
        User user = userRepository.findUserByUserName(theUser.getUserName());
        user.setUserName(theUser.getUserName());
        user.setPassword((theUser.getPassword()));
        user.setFirstName(theUser.getFirstName());
        user.setLastName(theUser.getLastName());
        user.setEmail(theUser.getEmail());
        theUser.setRoles(theUser.getRoles());
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return findByName(user.getUserName()) != null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                grantedAuthorities);
    }
}
