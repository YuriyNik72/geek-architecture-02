package ru.geekbrains.services;


import org.springframework.stereotype.Service;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductDto> getProducts(){
        return productMapper(productRepository.findAll());
    }
    private List<ProductDto> productMapper(List<Product> product){
        return product.stream().map(p->new ProductDto(p.getId(),p.getCost(), p.getTitle())).collect(Collectors.toList());
    }
}
