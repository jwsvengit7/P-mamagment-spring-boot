package com.maids.backendquiz.productsmanagement.domain.repository;

import com.maids.backendquiz.productsmanagement.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientByEmail(String email);
}
