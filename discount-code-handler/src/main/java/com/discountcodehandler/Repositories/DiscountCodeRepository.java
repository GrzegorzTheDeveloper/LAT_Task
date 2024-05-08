package com.discountcodehandler.Repositories;

import com.discountcodehandler.Models.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    List<DiscountCode> findAllPromoCodes();
    DiscountCode findByPromoCode(String promoCode);


}
