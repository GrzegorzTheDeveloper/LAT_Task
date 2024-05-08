package com.discountcodehandler.DTOs;

import com.discountcodehandler.Models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    @JsonProperty
    private Price price;

    @JsonProperty
    private String description;

}
