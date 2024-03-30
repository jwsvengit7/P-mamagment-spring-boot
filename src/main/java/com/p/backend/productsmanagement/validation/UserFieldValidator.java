package com.p.backend.productsmanagement.validation;

import com.p.backend.productsmanagement.validation.anotations.ValidateAppFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class UserFieldValidator implements ConstraintValidator<ValidateAppFields,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !value.isEmpty();
    }
}
