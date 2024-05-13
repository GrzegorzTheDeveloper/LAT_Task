package com.discountcodehandler.service;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.command.DiscountCodeCommand;
import com.discountcodehandler.model.dto.DiscountCodeDto;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountCodeService {

  private final DiscountCodeRepository discountCodeRepository;

  public List<DiscountCodeDto> findAll() {
    return discountCodeRepository.findAll().stream()
        .map(DiscountCodeDto::mapToDto)
        .toList();
  }

  public DiscountCodeDto getPromoCodeDetails(String promoCode) {
    return DiscountCodeDto.mapToDto(getDiscountCodeEntityByPromoCode(
        promoCode));
  }

  public DiscountCodeEntity getDiscountCodeEntityByPromoCode(String promoCode) {
    return discountCodeRepository.findByPromoCode(promoCode)
        .orElseThrow(DiscountCodeNotFoundException::new);
  }

  @Transactional
  public DiscountCodeDto create(DiscountCodeCommand command) {
    DiscountCodeEntity discountCodeEntity = command.mapFromCommand();
    return DiscountCodeDto.mapToDto(discountCodeRepository.save(discountCodeEntity));
  }

  @Transactional
  public void incrementNumberOfUses(String promoCode) {
    DiscountCodeEntity discountCodeEntity = getDiscountCodeEntityByPromoCode(promoCode);
    discountCodeEntity.setNumberOfUses(discountCodeEntity.getNumberOfUses() + 1);
    discountCodeRepository.save(discountCodeEntity);
  }


}
