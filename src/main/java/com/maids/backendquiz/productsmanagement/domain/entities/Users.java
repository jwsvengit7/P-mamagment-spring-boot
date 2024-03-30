package com.maids.backendquiz.productsmanagement.domain.entities;

import com.maids.backendquiz.productsmanagement.domain.enums.Role;
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
@Table(name = "USER_TB")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "USER_TB",allocationSize = 1,name = "USER_TB")
    private Long id;
    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(name = "user_email",nullable = false)
    private String email;
    @Column(name = "user_password",nullable = false)
    private String password;

    @Column(name = "user_role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}