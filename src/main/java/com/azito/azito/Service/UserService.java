package com.azito.azito.Service;


import com.azito.azito.Models.Product;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.ProductRepository;
import com.azito.azito.Repository.UserRepository;
import com.azito.azito.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ProductService productService;
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
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user = userRepository.save(user);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void addFavouritesToUser(User user, Long productId){
        Product product = productService.getProductById(productId);
        if (product != null && !user.getFavoritesProducts().contains(product)) {
            user.getFavoritesProducts().add(product);
            userRepository.save(user);
        }
    }
    public void deleteFavouritesToUser(User user, Long  productId){
        Product product = productService.getProductById(productId);
        if (product != null) {
            user.getFavoritesProducts().remove(product);
            userRepository.save(user);
        }
    }

    public List<Product> listUserFavourites(User user){
        return user.getFavoritesProducts();
    }

    public void deleteProductFromFavourites(Product product){
        for (User user : userRepository.findAll()){
            if(user.getFavoritesProducts().contains(product)) {
                user.getFavoritesProducts().remove(product);
                userRepository.save(user);
            }
            if (user.getProductList().contains(product)){
                user.getProductList().remove(product);

                System.out.println("1" + user.getProductList().size());
                userRepository.save(user);
                productService.deleteProductById(product.getId());
                System.out.println("1" + user.getProductList().size());
                for (Product product1 : user.getProductList()){
                    System.out.println("1");
                    System.out.println(product1.getTitle());
                }
            }
        }
    }

    public List<Product> listUserProduct(Long id) {
        return getUserById(id).getProductList();
    }
}
