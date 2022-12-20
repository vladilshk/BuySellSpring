package com.azito.azito.Repository;

import com.azito.azito.Models.Favourites;
import com.azito.azito.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Favourites findByUserId(Long userId);
}
