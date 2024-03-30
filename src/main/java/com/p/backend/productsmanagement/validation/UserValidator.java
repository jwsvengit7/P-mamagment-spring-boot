package com.p.backend.productsmanagement.validation;



import com.p.backend.productsmanagement.validation.anotations.ValidateRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class UserValidator implements ConstraintValidator<ValidateRole, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        List<String> users = Arrays.asList("ADMIN", "FINANCE","SALES","INVENTORY");
        return users.contains(role);
    }
}