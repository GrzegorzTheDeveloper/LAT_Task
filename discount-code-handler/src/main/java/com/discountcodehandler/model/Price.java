package com.discountcodehandler.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Price {
    @NotNull
    private double amount;
    @NotNull
    private String currency;

    public boolean doesCurrencyMatch(Price price){
        return currency.equalsIgnoreCase(price.currency);
    }

}
