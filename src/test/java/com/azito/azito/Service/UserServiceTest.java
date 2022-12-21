package com.azito.azito.Service;

import com.azito.azito.Models.User;
import com.azito.azito.Repository.UserRepository;
import com.azito.azito.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void changeUserActivity() {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        userService.saveUser(user);
        Assert.assertTrue(user.isActive());
        Optional<User> optional = Optional.of(user);
        Mockito.doReturn(optional).when(userRepository).findById(user.getId());
        userService.changeUserActivity(user.getId());
        Assert.assertFalse(optional.get().isActive());
    }

    @Test
    void changeUserRole() {
        User user = new User();
        Long id = 5L;
        user.setId(id);
        userService.saveUser(user);

        Set<Role> roles = user.getRoles();
        Assert.assertTrue(roles.contains(Role.ROLE_USER) && !roles.contains(Role.ROLE_ADMIN) );

        Optional<User> optional = Optional.of(user);
        Mockito.doReturn(optional).when(userRepository).findById(user.getId());
        userService.changeUserRole(user.getId());
        roles = optional.get().getRoles();
        Assert.assertTrue(!roles.contains(Role.ROLE_USER) && roles.contains(Role.ROLE_ADMIN) );

    }


    @Test
    void saveUser() {
        User user = new User();
        user.setName("name");
        user.setNumber(12345);
        user.setEmail("email@mail.ru");
        user.setPassword("1");
        boolean userIdCreated = userService.saveUser(user);
        Assert.assertTrue(userIdCreated);
        Assert.assertTrue(user.getRoles().contains(Role.ROLE_USER));
        Assert.assertTrue(user.isActive());
    }

    @Test
    void saveUserFailTest() {
        User user = new User();
        user.setName("name");
        user.setNumber(12345);
        user.setEmail("email@mail.ru");
        user.setPassword("1");

        Mockito.doReturn(new User()).when(userRepository).findByEmail("email@mail.ru");

        boolean userIdCreated = userService.saveUser(user);
        Assert.assertFalse(userIdCreated);
    }

    @Test
    void getUserByPrincipal() {
        Principal principal = null;
        Assert.assertTrue(userService.getUserByPrincipal(principal).getId() == null);
    }
}