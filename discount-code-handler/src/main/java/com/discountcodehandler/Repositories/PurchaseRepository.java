package com.discountcodehandler.Repositories;

import com.discountcodehandler.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
