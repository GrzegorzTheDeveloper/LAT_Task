package com.discountcodehandler.services;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.dtos.DiscountCode;
import com.discountcodehandler.repositories.DiscountCodeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountCodeService {

  private final DiscountCodeRepository discountCodeRepository;

  public List<String> findAllPromoCodes() {
    List<DiscountCodeEntity> codes = discountCodeRepository.findAll();
    List<String> promoCodes = new ArrayList<>();
    for (DiscountCodeEntity promo : codes) {
      promoCodes.add(promo.getPromoCode());
    }
    return promoCodes;
  }

  public DiscountCodeEntity getPromoCodeDetails(String promoCode)
      throws DiscountCodeNotFoundException {

    return discountCodeRepository.findByPromoCode(promoCode)
        .orElseThrow(DiscountCodeNotFoundException::new);
  }

  public DiscountCodeEntity addPromoCode(DiscountCode discountCode){
    DiscountCodeEntity discountCodeEntity = new DiscountCodeEntity();
    discountCodeEntity.setPromoCode(discountCode.getPromoCode());
    discountCodeEntity.setPriceEntity(discountCode.getPriceEntity());
    discountCodeEntity.setExpirationDate(discountCode.getExpirationDate());
    discountCodeEntity.setMaximalNumberOfUsage(discountCode.getMaximalNumberUsage());
    return discountCodeRepository.save(discountCodeEntity);
  }


}
