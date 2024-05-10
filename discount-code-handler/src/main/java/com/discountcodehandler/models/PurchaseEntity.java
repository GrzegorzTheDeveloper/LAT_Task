package com.discountcodehandler.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Purchase")
public class PurchaseEntity {

  @Column(name = "purchase_id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long purchaseId;

  @NotNull
  private Date date;

  @NotNull
  @Embedded
  private PriceEntity regularPriceEntity;


  private double discount;

  @NotNull
  private ProductEntity productEntity;

}
