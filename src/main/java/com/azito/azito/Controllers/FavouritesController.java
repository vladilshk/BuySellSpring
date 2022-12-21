package com.azito.azito.Controllers;


import com.azito.azito.Models.User;
import com.azito.azito.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FavouritesController {

    private final UserService userService;

    @GetMapping("/favourites")
    public String  getUserFavourites(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("products", userService.listUserFavourites(user));
        model.addAttribute("user", user);
        return "user/user-favourites";
    }

    @PostMapping("/favourites/add")
    public String  addProductToFavorites(Principal principal, Model model, @RequestParam(name = "productId") Long productId){
        userService.addFavouritesToUser(userService.getUserByPrincipal(principal), productId);
        return "redirect:/favourites";
    }

    @PostMapping("/favourites/delete")
    public String removeProductFromFavourites(Principal principal, @RequestParam(name = "productId") Long productId){
        userService.deleteFavouritesToUser(userService.getUserByPrincipal(principal), productId);
        return "redirect:/favourites";
    }

}
