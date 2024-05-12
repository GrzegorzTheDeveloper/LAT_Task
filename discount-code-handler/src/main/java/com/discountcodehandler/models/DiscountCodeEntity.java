package com.discountcodehandler.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
  @NotNull
  @NotBlank(message = "Promo code must not be blank")
  @Size(min = 3, max = 24, message = "Promo code must be between 3 and 24 characters.")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Promo code must contain only alphanumeric characters")
  private String promoCode;


  @NotNull
  private LocalDate expirationDate;

  @NotNull
  @Embedded
  private Price price;

  @NotNull
  private long maximalNumberOfUsage;

  @NotNull
  private long numberOfUses;

  public boolean isExpired() {
    LocalDate currentDate = LocalDate.now();
    return expirationDate != null && expirationDate.isBefore(currentDate);
  }

  public boolean isCodeUsed() {
    return maximalNumberOfUsage <= numberOfUses;
  }
}
