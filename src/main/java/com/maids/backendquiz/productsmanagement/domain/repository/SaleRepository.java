package com.maids.backendquiz.productsmanagement.domain.repository;

import com.maids.backendquiz.productsmanagement.domain.entities.Client;
import com.maids.backendquiz.productsmanagement.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository  extends JpaRepository<Sale,Long> {
    List<Sale> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
