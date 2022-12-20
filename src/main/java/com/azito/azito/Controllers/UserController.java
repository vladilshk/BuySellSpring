package com.azito.azito.Controllers;

import com.azito.azito.Models.User;
import com.azito.azito.Role;
import com.azito.azito.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "user/user-info";
    }

    @GetMapping("/profile")
    public String getProfile(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        if(user.getRoles().contains(Role.ROLE_ADMIN)){
            return "redirect:/admin/profile";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));

        return "user/login";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(User user){

        System.out.println("pososi");
        userService.saveUser(user);
        return "redirect:/login";
    }

}
