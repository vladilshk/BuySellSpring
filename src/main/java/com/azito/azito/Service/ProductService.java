package com.azito.azito.Service;
import com.azito.azito.Models.Product;
import com.azito.azito.Models.ProductImage;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.ProductImagesRepository;
import com.azito.azito.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImagesRepository imagesRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public List<Product> listProducts(String city, String searchWorld) {
        List<Product> productList = productRepository.findAll();
        if (searchWorld != null)
            searchWorld = searchWorld.toLowerCase();
        else if (city == null)
            return productList;


        productList = findByCity(productList, city);
        productList = findBySearchWorld(productList, searchWorld);
        return productList;
    }

    public List<Product> findByCity(List<Product> productList, String city) {
        if (city == null || city.equals("no"))
            return productList;

        List<Product> productsWithCurrentCity = new ArrayList<>();
        for (Product product : productList)
            if (product.getCity().equals(city))
                productsWithCurrentCity.add(product);

        return productsWithCurrentCity;
    }

    public List<Product> findBySearchWorld(List<Product> productList, String searchWorld) {
        if (searchWorld == null || searchWorld.equals(""))
            return productList;
        List<Product> searchedProducts = new ArrayList<>();
        for (Product product : productList) {
            boolean selected = false;
            String regex = " ";
            String[] titleWorlds = product.getTitle().split(regex);
            String[] descriptionWorlds = product.getDescription().split(regex);
            for (String world : titleWorlds)
                if (world.toLowerCase().equals(searchWorld)) {
                    searchedProducts.add(product);
                    selected = true;
                    break;
                }
            if (!selected)
                for (String world : descriptionWorlds)
                    if (world.toLowerCase().equals(searchWorld)) {
                        searchedProducts.add(product);
                        break;
                    }
        }
        return searchedProducts;
    }

    public List<Product> listProducts(User user) {
        List<Product> productList = productRepository.findAll();
        List<Product> usersProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getUser().getId().equals(user.getId()))
                usersProducts.add(product);
        }
        return usersProducts;
    }

    public void saveProduct(Product product, User user, MultipartFile image) throws IOException {
        product.setUser(user);
        product.setDateOfCreation(new Date().toString());
        product = productRepository.save(product);
        addImage(image, product);
    }

    private void addImage(MultipartFile file, Product product) throws IOException {
        ProductImage image = new ProductImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setProductId(product.getId());
        imagesRepository.save(image);
    }

    public void deleteProductById(Long productId, User user) {
        /*productRepository.deleteById(productId);
        userRepository.findById(user.getId()).orElse(null).getProductList().remove(productRepository.findById(productId));
*/
        productRepository.deleteById(productId);

    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void changeProductActivity(Long id) {
        Product product = getProductById(id);
    }

    public void editProduct(Long id, Product updatedProduct) {
        Product productFromDb = getProductById(id);

        productFromDb.setTitle(updatedProduct.getTitle());
        productFromDb.setDescription(updatedProduct.getDescription());
        productFromDb.setPrice(updatedProduct.getPrice());

        productRepository.save(productFromDb);

    }
}
