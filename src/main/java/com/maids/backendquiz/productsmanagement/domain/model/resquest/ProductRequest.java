package com.maids.backendquiz.productsmanagement.domain.model.resquest;

import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateAppFields;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @ValidateAppFields
    private String name;
    
    @Positive(message = "Price must be positive")
    private double price;
    @ValidateAppFields
    private String description;
    @ValidateAppFields
    private String category;

    private Integer  initialQuantity;




}
