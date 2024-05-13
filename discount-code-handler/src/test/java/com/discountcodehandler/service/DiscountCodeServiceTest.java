package com.discountcodehandler.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.Price;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DiscountCodeServiceTest {

  @Mock
  private DiscountCodeRepository discountCodeRepository;

  @InjectMocks
  private DiscountCodeService discountCodeService;

  public static DiscountCodeEntity createMockDiscountCode(String promoCode, double price,
      String currency, long maximalNumberOfUsage, LocalDate expirationDate) {

    Price mockPrice = mock(Price.class);
    when(mockPrice.getAmount()).thenReturn(price);
    when(mockPrice.getCurrency()).thenReturn(currency);

    DiscountCodeEntity mockDiscountCode = mock(DiscountCodeEntity.class);
    when(mockDiscountCode.getPromoCode()).thenReturn(promoCode);
    when(mockDiscountCode.getPrice()).thenReturn(mockPrice);
    when(mockDiscountCode.getMaximalNumberOfUsage()).thenReturn(maximalNumberOfUsage);
    when(mockDiscountCode.getExpirationDate()).thenReturn(expirationDate);

    return mockDiscountCode;
  }

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void DiscountCodeService_FindAllPromoCodes_ReturnPromoCodesList() {

    //Given
    String firsPromoCode = "abc";
    String secondPromoCode = "qwe";
    List<DiscountCodeEntity> mockCodes = Arrays.asList(
        createMockDiscountCode(firsPromoCode, 100,
            "EUR", 10,
            LocalDate.of(2024,
                Month.DECEMBER, 12)),
        createMockDiscountCode(secondPromoCode, 100,
            "DOL", 10,
            LocalDate.of(2024,
                Month.DECEMBER, 12))
    );
    int mockCodesSize = mockCodes.size();

    when(discountCodeRepository.findAll()).thenReturn(mockCodes);

    //When

    List<String> result = discountCodeService.findAllPromoCodes();

    //Then
    assertEquals(mockCodesSize, result.size());
    assertEquals(firsPromoCode, result.get(0));
    assertEquals(secondPromoCode, result.get(1));
//    verify(discountCodeService.findAllPromoCodes());

  }

  @Test
  void DiscountCodeService_FindPromoCodeDetails() {

    //Given
    DiscountCodeEntity mockCode = createMockDiscountCode("abc", 100,
        "EUR", 10,
        LocalDate.of(2024,
            Month.DECEMBER, 12));

    when(discountCodeRepository.findByPromoCode("abc")).thenReturn(Optional.of(mockCode));
    //When
    DiscountCodeEntity result = discountCodeService.getPromoCodeDetails("abc");

    //Then
    assertEquals(mockCode, result);

  }

  @Test
  void addPromoCode() {
  }

  @Test
  void useDiscountCode() {
  }
}