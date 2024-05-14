package com.discountcodehandler.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.Price;
import com.discountcodehandler.repositorie.DiscountCodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "classpath:code-cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DiscountCodeControllerTest {

  @Autowired
  private DiscountCodeRepository discountCodeRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void testCreate_ResultsInShapeBeingCreated() throws Exception {
    LocalDate localDate = LocalDate.now().plusYears(1);
    Price price = Price.builder()
        .amount(120)
        .currency("EUR")
        .build();
    DiscountCodeEntity newCode = DiscountCodeEntity.builder()
        .promoCode("bike10")
        .expirationDate(localDate)
        .price(price)
        .maximalNumberUsage(10L)
        .build();


    String priceJson = objectMapper.writeValueAsString(price);

    Map<String, Object> params = new HashMap<>();
    params.put("promoCode", newCode.getPromoCode());
    params.put("expirationDate", newCode.getExpirationDate());
    params.put("price", priceJson);
    params.put("maximalNumberUsage", newCode.getMaximalNumberUsage());

    mockMvc.perform(post("/promo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(params)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(newCode.getId()))
        .andExpect(jsonPath("$.promoCode").value(newCode.getPromoCode()))
        .andExpect(jsonPath("$.expirationDate").value(newCode.getExpirationDate()))
        .andExpect(jsonPath("$.price.currency").value(newCode.getPrice().getCurrency()))
        .andExpect(jsonPath("$.price.amount").value(newCode.getPrice().getAmount()))
        .andExpect(jsonPath("$.maximalNumberUsage").value(newCode.getMaximalNumberUsage()))
        .andReturn();
  }
}