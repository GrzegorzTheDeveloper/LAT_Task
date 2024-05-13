package com.discountcodehandler.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "Product")
public class ProductEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  private String name;

  @Embedded
  private Price price;

  private String description;


}
