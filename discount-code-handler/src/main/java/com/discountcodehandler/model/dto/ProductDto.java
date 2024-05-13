package com.discountcodehandler.model.dto;

import com.discountcodehandler.model.Price;
import com.discountcodehandler.model.ProductEntity;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ProductDto {
  private long id;
  private String name;
  private Price price;
  private String description;

  public static ProductDto mapToDto(ProductEntity product) {
    return ProductDto.builder()
        .id(product.getProductId())
        .name(product.getName())
        .price(product.getPrice())
        .description(product.getDescription())
        .build();
  }
}
