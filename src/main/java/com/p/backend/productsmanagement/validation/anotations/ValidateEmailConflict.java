package com.p.backend.productsmanagement.validation.anotations;

import com.p.backend.productsmanagement.validation.ConflictValidator;
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
