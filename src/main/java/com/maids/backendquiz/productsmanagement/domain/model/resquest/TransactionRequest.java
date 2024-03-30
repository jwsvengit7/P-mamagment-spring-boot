package com.maids.backendquiz.productsmanagement.domain.model.resquest;

import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateAppFields;
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
