package com.discountcodehandler.models.dtos;

import com.discountcodehandler.models.PriceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@RequiredArgsConstructor
@Getter
@Setter
public class Product {

    @JsonProperty
    private String name;
    @JsonProperty
    private PriceEntity priceEntity;
    @JsonProperty
    private String description;

}
