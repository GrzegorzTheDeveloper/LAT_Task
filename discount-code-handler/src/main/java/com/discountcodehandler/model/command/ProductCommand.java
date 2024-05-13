package com.discountcodehandler.model.command;

import com.discountcodehandler.model.Price;
import com.discountcodehandler.model.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductCommand {

  @NotNull
  private String name;
  @NotNull
  private Price price;
  private String description;

  public ProductEntity mapFromDto() {
    return ProductEntity.builder()
        .name(getName())
        .price(getPrice())
        .description(getDescription())
        .build();
  }
}
