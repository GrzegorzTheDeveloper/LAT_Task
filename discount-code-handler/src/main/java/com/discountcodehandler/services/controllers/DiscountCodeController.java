package com.discountcodehandler.services.controllers;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.models.DiscountCodeEntity;
import com.discountcodehandler.models.dtos.DiscountCode;
import com.discountcodehandler.services.DiscountCodeService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/promo")
public class DiscountCodeController {

  private DiscountCodeService discountCodeService;

  @Autowired
  public DiscountCodeController(DiscountCodeService discountCodeService) {
    this.discountCodeService = discountCodeService;
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleDiscountNotFoundException(
      DiscountCodeNotFoundException discountCodeNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such discount code");
  }


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
