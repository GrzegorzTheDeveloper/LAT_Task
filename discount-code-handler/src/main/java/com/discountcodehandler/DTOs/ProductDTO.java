package com.discountcodehandler.DTOs;

import com.discountcodehandler.Models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    @JsonProperty private Price price;

    @JsonProperty private String description;

    public ProductDTO(Price price) {

        this.price = price;

    }
}
