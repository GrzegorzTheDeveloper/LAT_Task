package com.discountcodehandler.service;

import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.models.dto.Product;
import com.discountcodehandler.repositorie.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

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

  public ProductEntity findById(Long id) {
    return productRepository.findById(id).orElseThrow(ProductNotFountException::new);
  }

}
