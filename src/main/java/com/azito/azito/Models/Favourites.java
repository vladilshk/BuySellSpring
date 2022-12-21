package com.azito.azito.Models;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "favorites")
@Data
@RequiredArgsConstructor
public class Favourites {
    @Id

    @Column(name = "user_id", unique = true)
    private Long userId;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    List<Product> productList = new ArrayList<>();

}
