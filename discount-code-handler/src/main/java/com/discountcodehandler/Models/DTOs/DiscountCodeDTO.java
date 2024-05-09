package com.discountcodehandler.Models.DTOs;

import com.discountcodehandler.Models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class DiscountCodeDTO {

    @JsonProperty
    private String promoCode;

    @JsonProperty
    private Date expirationDate;

    @JsonProperty
    private Price price;

    @JsonProperty
    private long maximalNumberUsage;

}
