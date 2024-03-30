package com.p.backend.productsmanagement.domain.repository;

import com.p.backend.productsmanagement.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientByEmail(String email);
}
