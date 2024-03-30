package com.maids.backendquiz.productsmanagement.domain.repository;

import com.maids.backendquiz.productsmanagement.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
