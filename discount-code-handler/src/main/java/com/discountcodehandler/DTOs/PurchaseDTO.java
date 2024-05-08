package com.discountcodehandler.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseDTO {

    @JsonProperty private Date date;

    @JsonProperty private double regularPrice;

    @JsonProperty private double discount;

    @JsonProperty private Long productId;

    public PurchaseDTO(Date date, double regularPrice, Long productId) {
        this.date = date;
        this.regularPrice = regularPrice;
        this.productId = productId;
    }
}
