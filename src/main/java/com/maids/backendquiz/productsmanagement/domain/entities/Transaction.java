package com.maids.backendquiz.productsmanagement.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION_TB")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "TRANSACTION_TB",allocationSize = 1,name = "TRANSACTION_TB")
    private Long id;
    private int quantity;
    private double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Sale sale;

}
