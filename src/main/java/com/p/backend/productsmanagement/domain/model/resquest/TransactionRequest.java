package com.p.backend.productsmanagement.domain.model.resquest;

import com.p.backend.productsmanagement.validation.anotations.ValidateAppFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    @ValidateAppFields
    private Long id;
    @ValidateAppFields
    private int quantity;
    @ValidateAppFields
    private double price;
}
