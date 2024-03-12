package com.microservices.productservice.utility;

/**
 * Utility class defining constants for validation messages in the application.
 * These constants are typically used as keys to retrieve localized validation messages.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
public class ValidationConstant {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private ValidationConstant() {
    }

    /**
     * Validation message key indicating that a product name cannot be blank.
     */
    public static final String PRODUCT_NAME_NOT_BLANK = "{validation.productname.notblank}";

    /**
     * Validation message key indicating that a product name must contain only alphabetic characters and spaces.
     */
    public static final String PRODUCT_NAME_ALPHA = "{validation.productname.alpha}";

    /**
     * Validation message key indicating the minimum size requirement for a product name.
     */
    public static final String PRODUCT_NAME_MIN_SIZE = "{validation.productname.minimumsize}";

    /**
     * Validation message key indicating the maximum size allowed for a product name.
     */
    public static final String PRODUCT_NAME_MAX_SIZE = "{validation.productname.maximumsize}";

    /**
     * Validation message key indicating the minimum value requirement for a product price.
     */
    public static final String PRODUCT_PRICE_MIN_VALUE = "{validation.productprice.minimumvalue}";

    /**
     * Validation message key indicating the maximum value allowed for a product price.
     */
    public static final String PRODUCT_PRICE_MAX_VALUE = "{validation.productprice.maximumvalue}";

    /**
     * Validation message key indicating the required price pattern for a product price.
     */
    public static final String PRODUCT_PRICE_PRICE_PATTERN = "{validation.productprice.pricepattern}";
}

