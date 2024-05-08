package com.discountcodehandler.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private double regularPrice;


    private double discount;

    @NotNull
    private Product product;

}
