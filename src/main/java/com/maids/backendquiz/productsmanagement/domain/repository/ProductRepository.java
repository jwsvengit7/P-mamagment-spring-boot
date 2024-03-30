package com.maids.backendquiz.productsmanagement.domain.repository;

import com.maids.backendquiz.productsmanagement.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
