package com.discountcodehandler.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.Price;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import com.discountcodehandler.service.DiscountCodeService;
import java.time.LocalDate;
import java.time.Month;
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
class DiscountCodeDtoRepositoryTest {

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
  void DiscountCodeRepository_FindByPromoCode_ReturnDiscountCode() {

    //Given
    DiscountCodeEntity mockDiscountCode = createMockDiscountCode("abc", 100,
        "EUR", 10,
        LocalDate.of(2024,
            Month.DECEMBER, 12));
    when(discountCodeRepository.findByPromoCode("abc")).thenReturn(
        Optional.of(mockDiscountCode));

    //When
    Optional<DiscountCodeEntity> result = discountCodeRepository.findByPromoCode("abc");

    //Then
    assertEquals(Optional.of(mockDiscountCode), result);


  }
}
