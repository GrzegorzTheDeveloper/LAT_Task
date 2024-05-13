package com.discountcodehandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such discount code")
public class DiscountCodeNotFoundException extends RuntimeException {

  private static final String MESSAGE = "Promo code not found";
  public DiscountCodeNotFoundException() {
    super(MESSAGE);
  }
}
