package com.maids.backendquiz.productsmanagement.domain.entities;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SALES_TB")
@EntityListeners(AuditingEntityListener.class)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "SALES_TB",allocationSize = 1,name = "SALES_TB")
    private Long id;

    private LocalDateTime creationDate;
    private LocalDateTime endDate;

    @ManyToOne
    private Users seller;
    @ManyToOne
    private Client client;
    private double total;
    private int qty;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
