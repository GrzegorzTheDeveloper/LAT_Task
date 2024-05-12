package com.discountcodehandler.services;

import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.DiscountPriceResult;
import com.discountcodehandler.models.Price;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.models.PurchaseEntity;
import com.discountcodehandler.models.dtos.Product;
import com.discountcodehandler.repositories.PurchaseRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final DiscountCodeService discountCodeService;

  private final ProductService productService;


  public DiscountPriceResult calculateDiscountPrice(String promoCode, Product product) {

    DiscountCodeEntity discountCode = discountCodeService.getPromoCodeDetails(promoCode);
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

  private static double getDiscountedPrice(DiscountCodeEntity discountCode, Price productPrice) {
    double discountedPrice = productPrice.getAmount() - discountCode.getPrice().getAmount();
    if (discountedPrice < 0) {
      discountedPrice = 0;
    }
    return discountedPrice;
  }

  public PurchaseEntity simulatePurchase(String promoCode, Long productId) {

    ProductEntity productEntity = productService.findById(productId);
    DiscountCodeEntity discountCodeEntity = discountCodeService.getPromoCodeDetails(promoCode);

    Product product = new Product();
    product.setPrice(productEntity.getPrice());

    DiscountPriceResult discountPriceResult = calculateDiscountPrice(promoCode, product);

    LocalDate date = LocalDate.now();

    PurchaseEntity purchaseEntity = PurchaseEntity.builder()
        .ProductName(product.getName())
        .date(date)
        .regularPrice(productEntity.getPrice())
        .build();

    if (discountPriceResult.getPrice() != productEntity.getPrice().getAmount()) {
      purchaseEntity.setDiscount(discountCodeEntity.getPrice().getAmount());
      discountCodeService.useDiscountCode(promoCode);
    }


    return purchaseRepository.save(purchaseEntity);

  }

}

