package com.okn.productInventory.controller;

import com.okn.productInventory.domain.Product;
import com.okn.productInventory.repository.ProductRepository;
import com.okn.productInventory.service.NotificationService;
import com.okn.productInventory.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ProductRepository repository;
    @Autowired private S3Service s3Service;
    @Autowired private NotificationService notificationService;

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Product create(
            @RequestParam("name") String name,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile image
            ) throws IOException {

        String imageUrl = s3Service.uploadFile(image.getOriginalFilename(), image.getInputStream());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setUrlImage(imageUrl);
        repository.save(product);

        //Notificar SQS
        notificationService.sendNotification("Created product: " + name);


        return product;
    }
}
