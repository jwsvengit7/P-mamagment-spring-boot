package com.maids.backendquiz.productsmanagement.domain.model.resquest;


import com.maids.backendquiz.productsmanagement.domain.enums.Role;
import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateAppFields;
import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateEmailConflict;
import com.maids.backendquiz.productsmanagement.exception.validation.anotations.ValidateRole;
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