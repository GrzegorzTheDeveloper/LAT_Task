package com.discountcodehandler.DTOs;

import com.discountcodehandler.Models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseDTO {
    @JsonProperty
    private Date date;

    @JsonProperty
    private double regularPrice;

    @JsonProperty
    private double discount;

    @JsonProperty
    private Product product;

}
