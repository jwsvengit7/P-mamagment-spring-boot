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
public class ClientRequest {
    @ValidateAppFields
    private String name;
    @ValidateAppFields
    private String lastName;
    @ValidateAppFields
    private String mobile;
    @ValidateAppFields
    private String email;
    @ValidateAppFields
    private String address;
}
