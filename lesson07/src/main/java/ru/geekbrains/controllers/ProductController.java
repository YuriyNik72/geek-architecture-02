package ru.geekbrains.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

        final ProductService productService;

        public ProductController(ProductService productService){
            this.productService = productService;
        }

        @GetMapping
    public List<ProductDto> getAllProducts(){
            return productService.getProducts();
        }

}
