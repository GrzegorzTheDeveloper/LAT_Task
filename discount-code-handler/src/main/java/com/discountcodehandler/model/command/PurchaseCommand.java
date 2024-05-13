package com.discountcodehandler.model.command;

import com.discountcodehandler.model.Price;
import com.discountcodehandler.model.PurchaseEntity;
import com.discountcodehandler.model.dto.PurchaseDto;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PurchaseCommand {
  @NotNull
  private LocalDate date;
  @NotNull
  private Price regularPrice;
  @NotNull
  private String productName;

  private double discount;

  public PurchaseEntity mapFromDto(PurchaseDto purchaseDto) {
    return PurchaseEntity.builder()
        .productName(purchaseDto.getProductName())
        .date(purchaseDto.getDate())
        .regularPrice(purchaseDto.getRegularPrice())
        .build();
  }
}
