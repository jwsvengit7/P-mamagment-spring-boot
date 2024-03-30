package com.p.backend.productsmanagement.validation.anotations;



import com.p.backend.productsmanagement.validation.UserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserValidator.class)
public @interface ValidateRole {

    public String message() default "Invalid Role: It should be either ADMIN,INVENTORY,SALES Or FINANCE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}