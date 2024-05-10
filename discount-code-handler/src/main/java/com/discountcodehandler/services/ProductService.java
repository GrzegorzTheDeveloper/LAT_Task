package com.discountcodehandler.services;

import com.discountcodehandler.models.dtos.Product;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;



    public ProductEntity addProduct(Product product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPriceEntity(product.getPriceEntity());
        productEntity.setDescription(product.getDescription());
        return productRepository.save(productEntity);
    }

    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

}
