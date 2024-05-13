package com.discountcodehandler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "DiscountCode")
public class DiscountCodeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "promoCode", unique = true)
  private String promoCode;

  private LocalDate expirationDate;

  @Embedded
  private Price price;

  private long maximalNumberOfUsage;

  private long numberOfUses;

  public boolean isExpired() {
    LocalDate currentDate = LocalDate.now();
    return expirationDate != null && expirationDate.isBefore(currentDate);
  }

  public boolean isCodeUsed() {
    return maximalNumberOfUsage <= numberOfUses;
  }
}
