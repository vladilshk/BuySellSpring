package com.azito.azito.Controllers;

import com.azito.azito.Models.Product;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.ProductRepository;
import com.azito.azito.Service.FavouritesService;
import com.azito.azito.Service.ProductService;
import com.azito.azito.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final FavouritesService favouritesService;
    private final ProductRepository productRepository;



    @GetMapping("/")
    public String getMainPage(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "searchWorld", required = false) String searchWorld ,Model model, Principal principal){
        model.addAttribute("products", productService.listProducts(city, searchWorld));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model, Principal principal){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "product/add-product";
    }

    @PostMapping("/product/add")
    public String saveProduct(Product product, Principal principal, @RequestParam("image") MultipartFile image) throws IOException {
        productService.saveProduct(product, userService.getUserByPrincipal(principal), image);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String getProductInfo(@PathVariable Long id, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("product", productService.getProductById(id, user));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "product/product-info";
    }

    //I don't know why, but if I want to delete product from service, it doesn't do this
    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam("productId") Long productId, Principal principal){
        productService.deleteProductById(productId);
        return "redirect:/my/products";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "product/edit-product";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Product product){
        productService.editProduct(id, product);
        return "redirect:/";
    }

    @GetMapping("/my/products")
    public String getUsersProduct(Principal principal, Model model){
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("products", productService.listProducts(user));
        model.addAttribute("user", user);
        return "user/user-products";
    }
}
