package com.discountcodehandler.repositories;

import com.discountcodehandler.models.DiscountCodeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCodeRepository extends JpaRepository<DiscountCodeEntity, Long> {

  Optional<DiscountCodeEntity> findByPromoCode(String promoCode);

}
