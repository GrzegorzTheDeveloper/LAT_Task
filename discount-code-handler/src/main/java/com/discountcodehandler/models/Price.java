package com.discountcodehandler.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Embeddable
public class Price {
    @NotNull
    private double price;
    @NotNull
    private String currency;

    public boolean doesCurrencyMatch(Price price){
        return currency.equals(price.currency);
    }

}
