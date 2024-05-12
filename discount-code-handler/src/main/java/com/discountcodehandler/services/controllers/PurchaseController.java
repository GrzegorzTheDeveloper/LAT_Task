package com.discountcodehandler.services.controllers;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.models.DiscountPriceResult;
import com.discountcodehandler.models.PurchaseEntity;
import com.discountcodehandler.models.dtos.Product;
import com.discountcodehandler.services.PurchaseService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

  @Autowired
  private PurchaseService purchaseService;

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleDiscountNotFoundException(
      DiscountCodeNotFoundException discountCodeNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such discount code");
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleProductNotFoundException(
      ProductNotFountException productNotFountException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such product");
  }



  @PostMapping("/getDiscountPrice/{promoCode}")
  public ResponseEntity<DiscountPriceResult> getDiscountPrice(@PathVariable String promoCode, @RequestBody Product product) {

    return ResponseEntity.ok(purchaseService.getDiscountPrice(promoCode, product));
  }

  @PostMapping("/simulatePurchase/{promoCode}/{productId}")
  public ResponseEntity<PurchaseEntity> simulatePurchase(@PathVariable String promoCode, @PathVariable Long productId){

    PurchaseEntity purchaseEntity = purchaseService.simulatePurchase(promoCode, productId);
    return ResponseEntity.created(URI.create("/purchase/" + purchaseEntity.getPurchaseId()))
        .body(purchaseEntity);
  }



}
