package com.discountcodehandler.Services;

import com.discountcodehandler.Models.DiscountCode;
import com.discountcodehandler.Repositories.DiscountCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository;

    public List<String> findAllPromoCodes() {
        List<DiscountCode> codes = discountCodeRepository.findAll();
        List<String> promo
    }

    public String getPromoCodeDetails(String promoCode){

    }

}
