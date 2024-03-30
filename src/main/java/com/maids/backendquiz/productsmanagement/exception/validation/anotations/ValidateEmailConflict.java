package com.maids.backendquiz.productsmanagement.exception.validation.anotations;

import com.maids.backendquiz.productsmanagement.exception.validation.ConflictValidator;
import com.maids.backendquiz.productsmanagement.exception.validation.UserFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConflictValidator.class)
public @interface ValidateEmailConflict {
    public String message()  default "Email Already Exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
