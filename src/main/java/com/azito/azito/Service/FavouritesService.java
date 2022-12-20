package com.azito.azito.Service;


import com.azito.azito.Models.Favourites;
import com.azito.azito.Models.Product;
import com.azito.azito.Models.User;
import com.azito.azito.Repository.FavouritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouritesService {
    private final FavouritesRepository favouritesRepository;

    public void addProductToFavourites(User user, Product product) {
        Favourites favourites = favouritesRepository.findByUserId(user.getId());
        if (favourites == null) {
            favourites = new Favourites();
            favourites.setUserId(user.getId());
            favourites.getProductList().add(product);
        } else if (!favourites.getProductList().contains(product))
            favourites.getProductList().add(product);
        favouritesRepository.save(favourites);
    }

    public Favourites getUserFavourites(User user) {
        Favourites favourites = favouritesRepository.findByUserId(user.getId());
        if (favourites == null)
            return new Favourites();
        return favourites;
    }

    public void removeProductFromFavourites(User user, Product product) {
        Favourites favourites = favouritesRepository.findByUserId(user.getId());
        List<Product> productList = favourites.getProductList();

        if (productList.contains(product))
            productList.remove(product);
        favourites.setProductList(productList);

        favouritesRepository.save(favourites);
    }

    public void removeProductFromFavourites(Product product) {
        for(Favourites favourites : favouritesRepository.findAll()){
            List<Product> productList = favourites.getProductList();
            if (productList.contains(product))
                productList.remove(product);
            favourites.setProductList(productList);
            favouritesRepository.save(favourites);
        }
    }
}
