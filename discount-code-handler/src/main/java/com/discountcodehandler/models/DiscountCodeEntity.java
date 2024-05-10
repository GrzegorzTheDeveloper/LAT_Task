package com.discountcodehandler.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "DiscountCode")
public class DiscountCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "promoCode", unique = true) //sprawdzic czy nie zmienic column na jakiegos uniqa
    @NotNull
    @NotBlank(message = "Promo code must not be blank")
    @Size(min = 3, max = 24, message = "Promo code must be between 3 and 24 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Promo code must contain only alphanumeric characters")
    private String promoCode;


    @NotNull
    private Date expirationDate;

    @NotNull
    @Embedded
    private PriceEntity priceEntity;

    @NotNull
    private long maximalNumberOfUsage;

    @NotNull
    private long numberOfUses;
}
