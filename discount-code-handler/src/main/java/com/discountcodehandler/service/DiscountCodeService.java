package com.discountcodehandler.service;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.dto.DiscountCode;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountCodeService {

  private DiscountCodeRepository discountCodeRepository;

  @Autowired
  public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
    this.discountCodeRepository = discountCodeRepository;
  }

  public List<String> findAllPromoCodes() {
    List<DiscountCodeEntity> codes = discountCodeRepository.findAll();
    List<String> promoCodes = new ArrayList<>();
    for (DiscountCodeEntity promo : codes) {
      promoCodes.add(promo.getPromoCode());
    }
    return promoCodes;
  }

  public DiscountCodeEntity getPromoCodeDetails(String promoCode) {

    return discountCodeRepository.findByPromoCode(promoCode)
        .orElseThrow(DiscountCodeNotFoundException::new);
  }


  public DiscountCodeEntity addPromoCode(DiscountCode discountCode) {
    DiscountCodeEntity discountCodeEntity = new DiscountCodeEntity();
    discountCodeEntity.setPromoCode(discountCode.getPromoCode());
    discountCodeEntity.setPrice(discountCode.getPrice());
    discountCodeEntity.setExpirationDate(discountCode.getExpirationDate());
    discountCodeEntity.setMaximalNumberOfUsage(discountCode.getMaximalNumberUsage());
    return discountCodeRepository.save(discountCodeEntity);
  }

  public void useDiscountCode(String promoCode){
    DiscountCodeEntity discountCodeEntity = getPromoCodeDetails(promoCode);
    discountCodeEntity.setNumberOfUses(discountCodeEntity.getNumberOfUses() + 1);
    discountCodeRepository.save(discountCodeEntity);
  }


}
