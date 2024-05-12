package com.discountcodehandler.repository;
import static org.assertj.core.api.Assertions.assertThat;
import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.Price;
import com.discountcodehandler.repositories.DiscountCodeRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DiscountCodeRepositoryTest {

  @Autowired
  private DiscountCodeRepository discountCodeRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void DiscountCodeRepository_FindByPromoCode_ReturnDiscountCode() {

    DiscountCodeEntity discountCode = DiscountCodeEntity.builder()
        .promoCode("abcd")
        .price(Price.builder()
            .amount(100).
            currency("dollar").
            build()).
        maximalNumberOfUsage(10)
        .expirationDate(
            LocalDate.of(2024, 6, 30)).build();

    entityManager.persist(discountCode);
    entityManager.flush();

    Optional<DiscountCodeEntity> foundDiscountCodeOptional = discountCodeRepository.findByPromoCode(
        discountCode.getPromoCode());

    assertThat(foundDiscountCodeOptional).isPresent();

    DiscountCodeEntity foundDiscountCode = foundDiscountCodeOptional.get();

    assertThat(foundDiscountCode).isNotNull();
    assertThat(foundDiscountCode.getPromoCode()).isEqualTo(discountCode.getPromoCode());
    assertThat(foundDiscountCode.getPrice().getAmount()).isEqualTo(discountCode.getPrice().getAmount());
    assertThat(foundDiscountCode.getPrice().getCurrency()).isEqualTo(discountCode.getPrice().getCurrency());
    assertThat(foundDiscountCode.getMaximalNumberOfUsage()).isEqualTo(discountCode.getMaximalNumberOfUsage());
    assertThat(foundDiscountCode.getExpirationDate()).isEqualTo(discountCode.getExpirationDate());


  }
}
