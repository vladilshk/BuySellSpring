package com.azito.azito.Service;


import com.azito.azito.Models.Product;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.UserRepository;
import com.azito.azito.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User getUserById(Long id) {
        userRepository.findById(id);
        return userRepository.findById(id).orElse(null);
    }

    public List<User> listUsers(String username) {

        if (username == null || username.equals(""))
            return userRepository.findAll();

        List<User> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            if (user.getName().equals(username)) {
                userList.add(user);
                break;
            }
        }
        return userList;
    }

    public void changeUserActivity(Long id) {
        User user = getUserById(id);
        if (user.isActive())
            user.setActive(false);
        else
            user.setActive(true);
        System.out.println("User with id" + +user.getId() + " activity changed to: " + user.getId() + " is " + user.isActive());
        userRepository.save(user);
    }

    public void changeUserRole(Long userId) {
        User user = getUserById(userId);
        Set<Role> userRoles = user.getRoles();
        if (userRoles.contains(Role.ROLE_ADMIN)) {
            userRoles = new HashSet<>();
            userRoles.add(Role.ROLE_USER);
        } else {
            userRoles = new HashSet<>();
            userRoles.add(Role.ROLE_ADMIN);
        }
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        System.out.println("User with id " + id + " was deleted");
    }

    public boolean saveUser(User user) {
        System.out.println("hello ((((");
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        System.out.println("salaaaam");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
