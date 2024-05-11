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




  public DiscountPriceResult getDiscountPrice(String promoCode, Product product) {

    DiscountCodeEntity discountCode = discountCodeService.getPromoCodeDetails(promoCode);
    Price productPrice = product.getPrice();
    DiscountPriceResult discountPriceResult = new DiscountPriceResult();
    discountPriceResult.setPrice(productPrice.getPrice());

    if (discountCode.isExpired())
      discountPriceResult.setWarning("Code has expired.");


    else if(!productPrice.doesCurrencyMatch(discountCode.getPrice()))
      discountPriceResult.setWarning("Currencies do not match");

    else if(discountCode.isCodeUsed())
      discountPriceResult.setWarning("Code has expired, maximal number of uses reached.");

    else{
      double discountPrice = productPrice.getPrice() - discountCode.getPrice().getPrice();
      if(discountPrice<0)
        discountPrice = 0;
      discountPriceResult.setPrice(discountPrice);
    }

    return discountPriceResult;

  }

  public PurchaseEntity simulatePurchase(String promoCode, Long productId){

    ProductEntity productEntity = productService.findById(productId);
    DiscountCodeEntity discountCodeEntity = discountCodeService.getPromoCodeDetails(promoCode);

    Product product = new Product();
    product.setPrice(productEntity.getPrice());

    DiscountPriceResult discountPriceResult = getDiscountPrice(promoCode, product);

    LocalDate date = LocalDate.now();

    PurchaseEntity purchaseEntity = new PurchaseEntity();
    purchaseEntity.setDate(date);
    if(discountPriceResult.getPrice() != productEntity.getPrice().getPrice()) {
      purchaseEntity.setDiscount(discountCodeEntity.getPrice().getPrice());
      discountCodeService.useDiscountCode(promoCode);
    }

    purchaseEntity.setProductName(productEntity.getName());
    purchaseEntity.setRegularPrice(productEntity.getPrice());

    return purchaseRepository.save(purchaseEntity);

  }

}

