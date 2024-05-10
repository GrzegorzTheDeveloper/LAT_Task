package com.discountcodehandler.models.dtos;

import com.discountcodehandler.models.PriceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class DiscountCode {

    @JsonProperty
    private String promoCode;

    @JsonProperty
    private Date expirationDate;

    @JsonProperty
    private PriceEntity priceEntity;

    @JsonProperty
    private long maximalNumberUsage;

}
