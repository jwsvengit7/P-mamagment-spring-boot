package com.p.backend.productsmanagement.domain.repository;

import com.p.backend.productsmanagement.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
