package com.p.backend.productsmanagement.domain.model.resquest;


import com.p.backend.productsmanagement.validation.anotations.ValidateAppFields;
import com.p.backend.productsmanagement.validation.anotations.ValidateEmailConflict;
import com.p.backend.productsmanagement.validation.anotations.ValidateRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {


    @ValidateAppFields
    private String name;

    @ValidateEmailConflict
    private String email;

    @ValidateAppFields
    private String password;

    @ValidateRole
    private String role;


}