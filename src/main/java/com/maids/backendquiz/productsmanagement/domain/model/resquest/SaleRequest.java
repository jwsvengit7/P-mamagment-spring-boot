package com.maids.backendquiz.productsmanagement.domain.model.resquest;

import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateAppFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {
    @ValidateAppFields
    private String clientEmail;
    @ValidateAppFields
    private String product;
    private double amount;
    private int qty;
    private List<TransactionRequest> transactionRequest;
}
