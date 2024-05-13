package com.discountcodehandler.controller;

import com.discountcodehandler.model.command.ProductCommand;
import com.discountcodehandler.model.dto.ProductDto;
import com.discountcodehandler.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDto> create(@RequestBody ProductCommand command) {
    return new ResponseEntity<>(productService.create(command), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ProductDto>> getAllProducts() {
      return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
  }
}
