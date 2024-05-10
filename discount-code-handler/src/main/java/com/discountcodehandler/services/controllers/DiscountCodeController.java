package com.discountcodehandler.services.controllers;

import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.dtos.DiscountCode;
import com.discountcodehandler.services.DiscountCodeService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/promo")
public class DiscountCodeController {

  private final DiscountCodeService discountCodeService;


  @GetMapping("/getAll")
  public ResponseEntity<List<String>> GetAllPromoCodes() {
    return ResponseEntity.ok(discountCodeService.findAllPromoCodes());
  }

  @GetMapping("/getDetails/{promoCode}")
  public ResponseEntity<DiscountCodeEntity> getDiscountCodeDetails(@PathVariable String promoCode) {
    return ResponseEntity.ok(discountCodeService.getPromoCodeDetails(promoCode));
  }

  @PostMapping("/addPromoCode")
  public ResponseEntity<DiscountCodeEntity> addDiscountCode(
      @RequestBody DiscountCode discountCode) {
    DiscountCodeEntity discountCodeEntity = discountCodeService.addPromoCode(discountCode);
    return ResponseEntity.created(URI.create("/promo/" + discountCodeEntity.getId()))
        .body(discountCodeEntity);
  }
}
