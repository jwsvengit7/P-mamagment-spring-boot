package com.maids.backendquiz.productsmanagement.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    SALES("Sales Manager"),
    ADMIN("Admin"),
    INVENTORY("Inventory Manager"),
    FINANCE("Finance Manager");
    private String name;
}
