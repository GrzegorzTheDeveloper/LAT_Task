package com.discountcodehandler.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Product")
public class ProductEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    private String name;

    @NotNull
    @Embedded
    private PriceEntity priceEntity;

    private String description;


}
