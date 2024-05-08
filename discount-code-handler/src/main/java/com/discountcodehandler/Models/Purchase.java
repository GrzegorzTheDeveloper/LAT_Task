package com.discountcodehandler.Models;

import jakarta.persistence.*;
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

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "regular_price", nullable = false)
    private double regularPrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "product_id")
    private Long productId;

}
