package com.discountcodehandler.DTOs;

import com.discountcodehandler.Models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DiscountCodeDTO {

    @JsonProperty private String promoCode;

    @JsonProperty private Date expirationDate;

    @JsonProperty private Price price;

    @JsonProperty private long maximalNumberUsage;
}
