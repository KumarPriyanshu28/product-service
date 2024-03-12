package com.microservices.productservice.dto.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/** Custom validator for enforcing a specific price pattern defined by the {@link PricePattern} annotation.
 * Validates whether a Double value adheres to the specified price pattern.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
public class PriceValidator implements ConstraintValidator<PricePattern, Double> {

    /**
     * Regular expression defining the expected price pattern.
     * The pattern allows whole numbers or numbers with up to two decimal places.
     */
    private static final String PRICE_PATTERN = "^\\d+(\\.\\d{1,2})?$";

    /**
     * Validates whether the provided Double value adheres to the specified price pattern.
     *
     * @param value   The Double value to be validated.
     * @param context The validation context.
     * @return True if the value adheres to the price pattern, false otherwise.
     */
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        String priceString = String.valueOf(value);
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        return pattern.matcher(priceString).matches();
    }
}
