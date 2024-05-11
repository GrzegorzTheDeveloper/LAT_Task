package com.discountcodehandler.services;

import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.models.dtos.Product;
import com.discountcodehandler.repositories.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {


  private final ProductRepository productRepository;


  public ProductEntity addProduct(Product product) {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setName(product.getName());
    productEntity.setPrice(product.getPrice());
    productEntity.setDescription(product.getDescription());
    return productRepository.save(productEntity);
  }

  public List<ProductEntity> getAllProducts() {
    return productRepository.findAll();
  }
  public ProductEntity findById(Long id){
    return productRepository.findById(id).orElseThrow(ProductNotFountException::new);
  }

}
