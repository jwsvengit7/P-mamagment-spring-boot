package com.maids.backendquiz.productsmanagement.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_TB")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "PRODUCT_TB",allocationSize = 1,name = "PRODUCT_TB")
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime creationDate;
    private Integer availableQuantity;
    private double price;

}
