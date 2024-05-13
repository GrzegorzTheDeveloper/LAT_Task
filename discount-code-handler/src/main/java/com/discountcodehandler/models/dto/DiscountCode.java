package com.discountcodehandler.models.dto;

import com.discountcodehandler.models.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class DiscountCode {

    @JsonProperty
    private String promoCode;

    @JsonProperty
    private LocalDate expirationDate;

    @JsonProperty
    private Price price;

    @JsonProperty
    private long maximalNumberUsage;

}
