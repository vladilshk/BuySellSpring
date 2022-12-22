package com.azito.azito.Controllers;


import com.azito.azito.Repository.ProductRepository;
import com.azito.azito.Service.ProductService;
import com.azito.azito.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;


    @GetMapping("/profile")
    public String adminProfile(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "admin/admin-profile";
    }

    @GetMapping("/users")
    public String getUsers(@RequestParam(value = "searchWorld",required = false) String username, Model model, Principal principal){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("users", userService.listUsers(username));
        return "admin/admin-users";
    }

    @PostMapping("/user/ban")
    public String banUser(@RequestParam(name = "userId") Long userId){
        userService.changeUserActivity(userId);
        return "redirect:/admin/users";
    }

    @PostMapping("/user/changerole")
    public String changeRole(@RequestParam(name = "userId") Long userId){
        userService.changeUserRole(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/products")
    public String getProducts(Model model, Principal principal){
        model.addAttribute("user", userService.getUserByPrincipal(principal));

        model.addAttribute("products", productService.listProducts());
        return "admin/admin-products";
    }

    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam(name = "productId") Long productId){
        userService.deleteProductFromFavourites(productService.getProductById(productId));
        return "redirect:/admin/products";
    }


}
