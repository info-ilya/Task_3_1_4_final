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
import ru.spring.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(User theUser) {
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
    }

    @Override
    public void updateUser(User theUser) {
        User user = userRepository.findUserByEmail(theUser.getEmail());
        if (theUser.getPassword().startsWith("$")) {
            user.setPassword(theUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(theUser.getPassword()));
        }
        user.setFirstName(theUser.getFirstName());
        user.setLastName(theUser.getLastName());
        user.setAge(theUser.getAge());
        user.setEmail(theUser.getEmail());
        user.setRoles(theUser.getRoles());
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
        return findByEmail(user.getEmail()) != null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                grantedAuthorities);
    }
}
