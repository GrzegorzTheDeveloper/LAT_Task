package com.discountcodehandler.models.dtos;

import com.discountcodehandler.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
public class Product {

    @JsonProperty
    private String name;
    @JsonProperty
    private Price price;
    @JsonProperty
    private String description;

}
