package com.discountcodehandler.service;

import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.model.ProductEntity;
import com.discountcodehandler.model.command.ProductCommand;
import com.discountcodehandler.model.dto.ProductDto;
import com.discountcodehandler.repositorie.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {


  private final ProductRepository productRepository;


  public ProductDto create(ProductCommand command) {
    ProductEntity productEntity = command.mapFromDto();
    return ProductDto.mapToDto(productRepository.save(productEntity));
  }

  public List<ProductDto> findAll() {
    return productRepository.findAll().stream()
        .map(ProductDto::mapToDto)
        .toList();

  }

  public ProductEntity findById(Long id) {
    return productRepository.findById(id).orElseThrow(ProductNotFountException::new);
  }

}
