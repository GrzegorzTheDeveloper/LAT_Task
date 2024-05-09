package com.discountcodehandler.Controllers;

import com.discountcodehandler.Models.DTOs.ProductDTO;
import com.discountcodehandler.Models.Product;
import com.discountcodehandler.Services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO){
        Product createdProduct = productService.addProduct(productDTO);
        return ResponseEntity.created(URI.create("/employees/" + createdProduct.getProductId())).body(createdProduct);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
