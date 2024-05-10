package com.discountcodehandler.services.controllers;

import com.discountcodehandler.models.dtos.Product;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.services.ProductService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;

  }

  @PostMapping("/addProduct")
  public ResponseEntity<ProductEntity> addProduct(@RequestBody Product product) {
    ProductEntity createdProductEntity = productService.addProduct(product);
    return ResponseEntity.created(URI.create("/products/" + createdProductEntity.getProductId()))
        .body(createdProductEntity);
  }

  @GetMapping("/getAllProducts")
  public ResponseEntity<List<ProductEntity>> getAllProducts() {
      return ResponseEntity.ok(productService.getAllProducts());
  }
}
