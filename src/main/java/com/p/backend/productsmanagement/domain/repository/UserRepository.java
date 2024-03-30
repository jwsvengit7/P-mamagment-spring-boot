package com.p.backend.productsmanagement.domain.repository;

import com.p.backend.productsmanagement.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);
    Users findUsersByEmail(String email);
    boolean existsByEmail(String email);
}
