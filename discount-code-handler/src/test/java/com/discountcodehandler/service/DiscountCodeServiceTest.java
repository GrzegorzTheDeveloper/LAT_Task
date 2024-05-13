package com.discountcodehandler.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.dto.DiscountCodeDto;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiscountCodeServiceTest {

  @Mock
  private DiscountCodeRepository discountCodeRepository;

  @InjectMocks
  private DiscountCodeService discountCodeService;

  @Captor
  private ArgumentCaptor<DiscountCodeEntity> codeEntityArgumentCaptor;


  @Test
  void testFindAll_ResultsInPromoCodesListBeingReturned() {
    //Given
    String firstPromoCode = "abc";
    String secondPromoCode = "qwe";

    List<DiscountCodeEntity> discountCodeEntityList = Arrays.asList(
        DiscountCodeEntity.builder()
            .id(1L)
            .promoCode(firstPromoCode)
            .build(),
        DiscountCodeEntity.builder()
            .id(2L)
            .promoCode(secondPromoCode)
            .build()
    );

    when(discountCodeRepository.findAll()).thenReturn(discountCodeEntityList);

    //When
    List<DiscountCodeDto> result = discountCodeService.findAll();

    //Then
    verify(discountCodeRepository).findAll();
    assertEquals(discountCodeEntityList.size(), result.size());
    assertEquals(firstPromoCode, result.get(0).getPromoCode());
    assertEquals(secondPromoCode, result.get(1).getPromoCode());
    verifyNoMoreInteractions(discountCodeRepository);
  }

  @Test
  void testGetPromoCodeDetails_HappyPath_ResultInDiscountCodeEntityBeingReturned() {
    //Given
    String promoCode = "abc";
    DiscountCodeEntity code = DiscountCodeEntity.builder()
        .id(1L)
        .promoCode(promoCode)
        .build();
    when(discountCodeRepository.findByPromoCode(promoCode)).thenReturn(Optional.of(code));

    //When
    DiscountCodeDto result = discountCodeService.getPromoCodeDetails(promoCode);

    //Then
    verify(discountCodeRepository).findByPromoCode(promoCode);
    assertEquals(code.getPromoCode(), result.getPromoCode());
    verifyNoMoreInteractions(discountCodeRepository);
  }

  @Test
  void testGetPromoCodeDetails_DiscountCodeNotFound_ResultInDiscountCodeNotFoundException() {
    //Given
    String promoCode = "abc";
    String exceptionMessage = "Promo code not found";
    //When
    assertThatExceptionOfType(DiscountCodeNotFoundException.class)
        .isThrownBy(() -> discountCodeService.getPromoCodeDetails(promoCode))
        .withMessage(exceptionMessage);
    //Then
    verify(discountCodeRepository).findByPromoCode(promoCode);
    verifyNoMoreInteractions(discountCodeRepository);

  }

//  @Test
//  void testCreate_HappyPath_ResultsInDiscountCodeBeingSaved() {
//    //Given
//    String promoCode = "abc";
//    DiscountCodeCommand codeCommand = DiscountCodeCommand.builder()
//        .promoCode(promoCode)
//        .build();
//
//    DiscountCodeEntity codeEntity = DiscountCodeEntity.builder()
//        .id(1L)
//        .promoCode(promoCode)
//        .build();
//
//    DiscountCodeDto codeDto = DiscountCodeDto.builder()
//        .id(1L)
//        .promoCode(promoCode)
//        .build();
//
//
//    //When
//   discountCodeService.create(codeCommand);
//    //Then
//    verify(discountCodeRepository).save(codeEntityArgumentCaptor.capture());
//    DiscountCodeEntity savedCode = codeEntityArgumentCaptor.getValue();
//    assertEquals(codeEntity.getPromoCode(),savedCode.getPromoCode());
//
//  }

  @Test
  void testIncrementNumberOfUses_HappyPath_ResultInIncrementationNumberOfUses() {
    //Given
    String promoCode = "abc";
    int uses = 10;
    DiscountCodeEntity code = DiscountCodeEntity.builder()
        .id(1L)
        .promoCode(promoCode)
        .numberOfUses(uses)
        .build();
    when(discountCodeRepository.findByPromoCode(promoCode)).thenReturn(Optional.ofNullable(code));
    //When
    discountCodeService.incrementNumberOfUses(promoCode);
    //Then
    verify(discountCodeRepository).save(codeEntityArgumentCaptor.capture());
    DiscountCodeEntity savedCode = codeEntityArgumentCaptor.getValue();
    assertEquals(uses + 1, savedCode.getNumberOfUses());
  }

}