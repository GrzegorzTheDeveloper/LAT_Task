package com.discountcodehandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Product not found")
public class ProductNotFountException extends RuntimeException{

  private static final String MESSAGE = "Product not found";
  public ProductNotFountException() {
    super(MESSAGE);
  }
}
