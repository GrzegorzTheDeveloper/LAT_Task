package com.discountcodehandler.repositories;

import com.discountcodehandler.models.ProductEntity;
import com.discountcodehandler.models.dtos.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
