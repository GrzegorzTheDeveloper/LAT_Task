package com.discountcodehandler.controller;

import com.discountcodehandler.exception.DiscountCodeNotFoundException;
import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.command.DiscountCodeCommand;
import com.discountcodehandler.model.dto.DiscountCodeDto;
import com.discountcodehandler.service.DiscountCodeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/promo")
public class DiscountCodeController {

  private final DiscountCodeService discountCodeService;

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handleDiscountNotFoundException(
      DiscountCodeNotFoundException discountCodeNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(discountCodeNotFoundException.getMessage());
  }


  @GetMapping
  public ResponseEntity<List<DiscountCodeDto>> getAll() {
    return new ResponseEntity<>(discountCodeService.findAll(), HttpStatus.OK);
  }


  @GetMapping("/{promoCode}")
  public ResponseEntity<DiscountCodeDto> getDiscountCodeDetails(@PathVariable String promoCode) {
    return new ResponseEntity<>(discountCodeService.getPromoCodeDetails(promoCode),HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<DiscountCodeDto> create(@RequestBody @Valid DiscountCodeCommand command) {
    return new ResponseEntity<>(discountCodeService.create(command), HttpStatus.CREATED);
  }

}
