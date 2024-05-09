package com.discountcodehandler.Models.DTOs;

import com.discountcodehandler.Models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @JsonProperty
    private String name;
    @JsonProperty
    private Price price;
    @JsonProperty
    private String description;

}
