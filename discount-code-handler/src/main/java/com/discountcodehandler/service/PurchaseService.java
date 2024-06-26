package com.discountcodehandler.service;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.DiscountPriceResult;
import com.discountcodehandler.model.Price;
import com.discountcodehandler.model.PurchaseEntity;
import com.discountcodehandler.model.dto.ProductDto;
import com.discountcodehandler.model.dto.PurchaseDto;
import com.discountcodehandler.repositorie.PurchaseRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final DiscountCodeService discountCodeService;
  private final ProductService productService;


  private static double getDiscountedPrice(DiscountCodeEntity discountCode, Price productPrice) {
    double discountedPrice = productPrice.getAmount() - discountCode.getPrice().getAmount();
    if (discountedPrice < 0) {
      discountedPrice = 0;
    }
    return discountedPrice;
  }

  public DiscountPriceResult calculateDiscountPrice(long id, String promoCode) {

    DiscountCodeEntity discountCode = discountCodeService.getDiscountCodeEntityByPromoCode(
        promoCode);
    ProductDto product = productService.findById(id);
    Price productPrice = product.getPrice();
    DiscountPriceResult discountPriceResult = new DiscountPriceResult();
    discountPriceResult.setPrice(productPrice.getAmount());

    if (discountCode.isExpired()) {
      discountPriceResult.setWarning("Code has expired.");
    } else if (!productPrice.doesCurrencyMatch(discountCode.getPrice())) {
      discountPriceResult.setWarning("Currencies do not match");
    } else if (discountCode.isCodeUsed()) {
      discountPriceResult.setWarning("Code has expired, maximal number of uses reached.");
    } else {
      double discountPrice = getDiscountedPrice(discountCode, productPrice);
      discountPriceResult.setPrice(discountPrice);
    }

    return discountPriceResult;

  }

  public PurchaseDto create(long productId, String promoCode) {

    ProductDto productEntity = productService.findById(productId);
    DiscountCodeEntity discountCodeEntity = discountCodeService.getDiscountCodeEntityByPromoCode(
        promoCode);
    DiscountPriceResult discountPriceResult = calculateDiscountPrice(productId, promoCode);
    LocalDate date = LocalDate.now();

    PurchaseEntity purchaseEntity = PurchaseEntity.builder()
        .productName(productEntity.getName())
        .date(date)
        .regularPrice(productEntity.getPrice())
        .build();

    if (discountPriceResult.getPrice() != productEntity.getPrice().getAmount()) {
      purchaseEntity.setDiscount(discountCodeEntity.getPrice().getAmount());
      discountCodeService.incrementNumberOfUses(promoCode);
    }

    return PurchaseDto.mapToDto(purchaseRepository.save(purchaseEntity));

  }
}


