package com.p.backend.productsmanagement.domain.repository;

import com.p.backend.productsmanagement.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
