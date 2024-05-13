package com.discountcodehandler.repositorie;

import com.discountcodehandler.models.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
