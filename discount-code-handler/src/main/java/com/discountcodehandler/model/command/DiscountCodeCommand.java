package com.discountcodehandler.model.command;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.Price;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

@Data
public class DiscountCodeCommand {

  @NotNull
  @NotBlank(message = "Promo code must not be blank")
  @Size(min = 3, max = 24, message = "Promo code must be between 3 and 24 characters.")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Promo code must contain only alphanumeric characters")
  private String promoCode;
  @NotNull
  private LocalDate expirationDate;
  @NotNull
  private Price price;
  @NotNull
  private long maximalNumberUsage;


  public DiscountCodeEntity mapFromCommand() {
    return DiscountCodeEntity.builder()
        .promoCode(getPromoCode())
        .price(getPrice())
        .maximalNumberUsage(getMaximalNumberUsage())
        .expirationDate(getExpirationDate())
        .build();
  }

}
