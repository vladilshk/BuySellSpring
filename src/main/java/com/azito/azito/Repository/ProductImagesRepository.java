package com.azito.azito.Repository;

import com.azito.azito.Models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImage, Long> {
}
