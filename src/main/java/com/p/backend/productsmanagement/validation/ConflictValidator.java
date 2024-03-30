package com.p.backend.productsmanagement.validation;

import com.p.backend.productsmanagement.domain.repository.UserRepository;
import com.p.backend.productsmanagement.validation.anotations.ValidateEmailConflict;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConflictValidator implements ConstraintValidator<ValidateEmailConflict,String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(value);
    }
}
