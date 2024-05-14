package com.discountcodehandler.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.discountcodehandler.exception.ProductNotFountException;
import com.discountcodehandler.model.DiscountCodeEntity;
import com.discountcodehandler.model.ProductEntity;
import com.discountcodehandler.model.dto.ProductDto;
import com.discountcodehandler.repositorie.ProductRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    //Given
    String firstName = "abc";
    String secondName = "def";

    List<ProductEntity> productEntityList = Arrays.asList(
        ProductEntity.builder()
            .productId(1L)
            .name(firstName)
            .build(),
        ProductEntity.builder()
            .name(secondName)
            .productId(2L)
            .build()
    );

    when(productRepository.findAll()).thenReturn(productEntityList);

    //When

    List<ProductEntity> result = productRepository.findAll();

    //Then
    verify(productRepository).findAll();
    assertEquals(productEntityList.size(), result.size());
    assertEquals(firstName, result.get(0).getName());
    assertEquals(secondName, result.get(1).getName());
    verifyNoMoreInteractions(productRepository);

  }

  @Test
  void testFindById_HappyPath_ResultInProductEntityBeingReturned(){
    //Given
    long id = 1L;
    String name = "abc";

    ProductEntity product = ProductEntity.builder()
        .productId(id)
        .name(name)
        .build();

    when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
    //When
    ProductDto result = productService.findById(id);
    //Then
    verify(productRepository).findById(id);
    assertEquals(product.getName(), result.getName());
    verifyNoMoreInteractions(productRepository);
  }

  @Test
  void testFindById_ProductNotFound_ResultInProductNotFoundException(){
    //Given
    long id = 1L;
    String name = "abc";
    String exceptionMessage = "Product not found";
    //When
    assertThatExceptionOfType(ProductNotFountException.class)
        .isThrownBy(() -> productService.findById(id)).withMessage(exceptionMessage)
            .withMessage(exceptionMessage);
    //Then
    verify(productRepository).findById(id);
    verifyNoMoreInteractions(productRepository);
  }

}