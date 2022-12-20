package com.azito.azito.Models;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;

    @Column(name = "views")
    private long views;

    @Column(name = "date")
    private String dateOfCreation;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;


    /*@ManyToMany(*//*cascade = CascadeType.ALL,*//* fetch = FetchType.LAZY)
    private List<Favourites> favourites;*/
}
