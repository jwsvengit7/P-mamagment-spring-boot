package com.maids.backendquiz.productsmanagement.validation;

import com.maids.backendquiz.productsmanagement.validation.anotations.ValidateAppFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class UserFieldValidator implements ConstraintValidator<ValidateAppFields,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !value.isEmpty();
    }
}
