package com.discountcodehandler.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    private String name;

    @NotNull
    @Embedded
    private Price price;

    private String description;


}
