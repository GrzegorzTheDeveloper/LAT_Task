package com.discountcodehandler.initializer;

import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.Price;
import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import com.discountcodehandler.repositorie.ProductRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements ApplicationRunner {

  private final ProductRepository productRepository;

  private final DiscountCodeRepository discountCodeRepository;

  @Autowired
  public DbInit(ProductRepository productRepository,
      DiscountCodeRepository discountCodeRepository) {
    this.productRepository = productRepository;
    this.discountCodeRepository = discountCodeRepository;
  }


  @Override
  public void run(ApplicationArguments args) throws Exception {
    discountCodeRepository.save(DiscountCodeEntity.builder()
        .promoCode("BIKE10")
        .expirationDate(LocalDate.parse("2024-12-31"))
        .price(Price.builder().amount(10.00).currency("USD").build())
        .maximalNumberOfUsage(100)
        .numberOfUses(0)
        .build());

    discountCodeRepository.save(DiscountCodeEntity.builder()
        .promoCode("BIKE20")
        .expirationDate(LocalDate.parse("2024-12-31"))
        .price(Price.builder().amount(20.00).currency("USD").build())
        .maximalNumberOfUsage(200)
        .numberOfUses(0)
        .build());

    discountCodeRepository.save(DiscountCodeEntity.builder()
        .promoCode("BIKE25EUR")
        .expirationDate(LocalDate.parse("2024-12-31"))
        .price(Price.builder().amount(25.00).currency("EUR").build())
        .maximalNumberOfUsage(150)
        .numberOfUses(0)
        .build());

    discountCodeRepository.save(DiscountCodeEntity.builder()
        .promoCode("SUMMER15")
        .expirationDate(LocalDate.parse("2024-08-31"))
        .price(Price.builder().amount(15.00).currency("USD").build())
        .maximalNumberOfUsage(50)
        .numberOfUses(0)
        .build());

    discountCodeRepository.save(DiscountCodeEntity.builder()
        .promoCode("BIKE50")
        .expirationDate(LocalDate.parse("2024-12-31"))
        .price(Price.builder().amount(50.00).currency("USD").build())
        .maximalNumberOfUsage(300)
        .numberOfUses(0)
        .build());

    // Insert dummy data for ProductEntity
    productRepository.save(ProductEntity.builder()
        .name("Mountain Bike")
        .price(Price.builder().amount(500.00).currency("USD").build())
        .description("A rugged mountain bike designed for off-road adventures.")
        .build());

    productRepository.save(ProductEntity.builder()
        .name("Road Bike")
        .price(Price.builder().amount(750.00).currency("USD").build())
        .description("A sleek road bike optimized for speed on paved roads.")
        .build());

    productRepository.save(ProductEntity.builder()
        .name("Hybrid Bike")
        .price(Price.builder().amount(600.00).currency("USD").build())
        .description("A versatile hybrid bike suitable for city commuting and light trails.")
        .build());

    productRepository.save(ProductEntity.builder()
        .name("Electric Bike")
        .price(Price.builder().amount(1200.00).currency("USD").build())
        .description("An eco-friendly electric bike with pedal assist for effortless riding.")
        .build());

    productRepository.save(ProductEntity.builder()
        .name("Kids Bike")
        .price(Price.builder().amount(150.00).currency("USD").build())
        .description("A fun and colorful bike specially designed for kids.")
        .build());
  }
}
