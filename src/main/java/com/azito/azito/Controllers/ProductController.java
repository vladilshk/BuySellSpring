package com.azito.azito.Controllers;

import com.azito.azito.Models.Product;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.ProductRepository;
import com.azito.azito.Service.ProductService;
import com.azito.azito.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final ProductRepository productRepository;


    @GetMapping("/")
    public String getMainPage(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "searchWorld", required = false) String searchWorld ,Model model, Principal principal){
        model.addAttribute("products", productService.listProducts(city, searchWorld));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        if (user.getName() == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "product/add-product";
    }

    @PostMapping("/product/add")
    public String saveProduct(Product product, Principal principal, @RequestParam("image") MultipartFile image) throws IOException {
        if (userService.getUserByPrincipal(principal).getName() == null) return "redirect:/login";
        productService.saveProduct(product, userService.getUserByPrincipal(principal), image);
        return "redirect:/";
    }

    @GetMapping("/product/{id}")
    public String getProductInfo(@PathVariable Long id, Model model, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Product product = productService.getProductById(id, user);
        if (product == null)
            return "redirect:/";
        if (user.getName() == null){
            model.addAttribute("show", false);
        } else {
            model.addAttribute("show", true);
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("product", product);

        return "product/product-info";
    }

    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam("productId") Long productId, Principal principal){
        userService.deleteProductFromFavourites(productService.getProductById(productId));
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

    @PostMapping("/product/change/active")
    public String changeActivity(@RequestParam("productId") Long productId){
        productService.changeProductActivity(productId);
        return "redirect:/my/products";
    }
}
