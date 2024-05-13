package com.discountcodehandler.repositorie;

import com.discountcodehandler.model.DiscountCodeEntity;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface DiscountCodeRepository extends JpaRepository<DiscountCodeEntity, Long> {

  Optional<DiscountCodeEntity> findByPromoCode(String promoCode);

}
