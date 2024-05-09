package com.discountcodehandler.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Entity
@Table(name = "Purchase")
public class Purchase {

    @Column(name = "purchase_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @NotNull
    private Date date;

    @NotNull
    @Embedded
    private Price regularPrice;


    private double discount;

    @NotNull
    private Product product;

}
