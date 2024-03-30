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
@Table(name = "CLIENT_TB")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "CLIENT_TB",allocationSize = 1,name = "CLIENT_TB")
    private Long id;
    @Column(name = "client_name",nullable = false)
    private String name;
    @Column(name = "client_lastname",nullable = false)
    private String lastName;
    @Column(name = "client_phone",nullable = false)
    private String mobile;
    @Column(name = "client_email",nullable = false)
    private String email;

    @Column(name = "client_address",nullable = false)
    private String address;

}
