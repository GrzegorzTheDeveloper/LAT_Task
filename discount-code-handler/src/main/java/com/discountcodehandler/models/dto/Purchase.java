package com.discountcodehandler.models.dto;

import com.discountcodehandler.models.ProductEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Purchase {
    @JsonProperty
    private LocalDate date;

    @JsonProperty
    private double regularPrice;

    @JsonProperty
    private double discount;

    @JsonProperty
    private ProductEntity productEntity;

}
