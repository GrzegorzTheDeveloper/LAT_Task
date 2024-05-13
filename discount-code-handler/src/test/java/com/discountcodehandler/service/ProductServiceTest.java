package com.discountcodehandler.service;

import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.ProductEntity;
import com.discountcodehandler.repositorie.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;


  @Test
  void testFindAll_HappyPath_ResultsInProductDtoListBeingReturned() {

    List<ProductEntity> productEntityList = Arrays.asList(
        DiscountCodeEntity.builder()
            .id
    )
  }

}