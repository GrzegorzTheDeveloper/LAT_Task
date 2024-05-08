package com.discountcodehandler.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DiscountCode")
public class DiscountCode {

    @Column(name =  "code_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "promo_code", unique = true, nullable = false)
    private String promoCode;

    @Getter @Setter
    @Column(name = "expiration_date", nullable = false)
    private Date expirationDate;

    @Getter @Setter
    @Column(name = "price", nullable = false)
    private Price price;



    @Getter @Setter
    @Column(name = "maximal_number_of_usage", nullable = false)
    private long maximalNumberOfUsage;



}
