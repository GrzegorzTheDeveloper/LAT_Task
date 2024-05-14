package com.discountcodehandler.controller;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.model.DiscountPriceResult;
import com.discountcodehandler.model.dto.PurchaseDto;
import com.discountcodehandler.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;


  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleDiscountNotFoundException(
      DiscountCodeNotFoundException discountCodeNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(discountCodeNotFoundException.getMessage());
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleProductNotFoundException(
      ProductNotFountException productNotFountException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productNotFountException.getMessage());
  }


  @GetMapping("/getDiscountPrice/{id}/{promoCode}")
  public ResponseEntity<DiscountPriceResult> getDiscountPrice(@PathVariable long id,
      @PathVariable String promoCode) {/**/
    return new ResponseEntity<>(purchaseService.calculateDiscountPrice(id, promoCode),
        HttpStatus.OK);
  }

  @PostMapping("/simulatePurchase/{productId}/{promoCode}")
  public ResponseEntity<PurchaseDto> simulatePurchase(@PathVariable String promoCode,
      @PathVariable long productId) {
    return new ResponseEntity<>(purchaseService.create(productId, promoCode), HttpStatus.CREATED);
  }


}
