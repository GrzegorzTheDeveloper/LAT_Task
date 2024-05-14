package com.discountcodehandler.model.dto;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.Price;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiscountCodeDto {

  private long id;
  private String promoCode;

  private LocalDate expirationDate;

  private Price price;

  private long maximalNumberUsage;

  private long numberOfUses;


  public static DiscountCodeDto mapToDto(DiscountCodeEntity code) {
    return DiscountCodeDto.builder()
        .id(code.getId())
        .expirationDate(code.getExpirationDate())
        .promoCode(code.getPromoCode())
        .price(code.getPrice())
        .maximalNumberUsage(code.getMaximalNumberUsage())
        .numberOfUses(code.getNumberOfUses())
        .build();
  }
}
