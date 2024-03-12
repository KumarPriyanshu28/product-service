package com.microservices.productservice.dto.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation for enforcing a specific price pattern.
 * This annotation is typically applied to fields or parameters of type Double.
 * The validation logic is implemented in the associated {@link PriceValidator} class.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceValidator.class)
public @interface PricePattern {

    /**
     * Returns the default error message template for validation failure.
     *
     * @return The default error message template.
     */
    String message();

    /**
     * Returns the validation groups to which this constraint belongs.
     *
     * @return The validation groups.
     */
    Class<?>[] groups() default {};

    /**
     * Returns additional payload information for this constraint.
     *
     * @return The payload information.
     */
    Class<? extends Payload>[] payload() default {};
}
