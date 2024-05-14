package com.discountcodehandler.model.dto;

import com.discountcodehandler.model.Price;
import com.discountcodehandler.model.PurchaseEntity;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseDto {

  private long id;
  private LocalDate date;

  private Price regularPrice;

  private String productName;

  private double discount;

  public static PurchaseDto mapToDto(PurchaseEntity purchase) {
    return PurchaseDto.builder()
        .id(purchase.getPurchaseId())
        .productName(purchase.getProductName())
        .date(purchase.getDate())
        .discount(purchase.getDiscount())
        .regularPrice(purchase.getRegularPrice())
        .build();
  }
}
