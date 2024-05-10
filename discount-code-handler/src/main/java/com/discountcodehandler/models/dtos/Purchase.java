package com.discountcodehandler.models.dtos;

import com.discountcodehandler.models.ProductEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class Purchase {
    @JsonProperty
    private Date date;

    @JsonProperty
    private double regularPrice;

    @JsonProperty
    private double discount;

    @JsonProperty
    private ProductEntity productEntity;

}
