package com.azito.azito.Controllers;

import com.azito.azito.Models.Product;
import com.azito.azito.Models.ProductImage;
import com.azito.azito.Repository.ProductImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayInputStream;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ProductImagesRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        ProductImage image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("filename", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
