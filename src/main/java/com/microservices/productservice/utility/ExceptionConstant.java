package com.microservices.productservice.utility;

/**
 * Utility class defining constants for exception messages in the application.
 * These constants are typically used to identify and retrieve localized exception messages.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
public class ExceptionConstant {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private ExceptionConstant() {
    }

    /**
     * Exception key for indicating that a product list is empty while executing getAllProducts method.
     */
    public static final String GET_ALL_PRODUCTS_NO_CONTENT = "error.emptyproductlist.getallproducts";
    /**
     * Exception key for indicating that a product list is empty while executing getAllProductsSortedByPrice method.
     */
    public static final String GET_ALL_PRODUCTS_SORTED_BY_PRICE_NO_CONTENT = "error.emptyproductlist.getallproductssortedbyprice";
    /**
     * Exception key for indicating that a product list is empty while executing getProductsByPriceRange method.
     */
    public static final String GET_PRODUCTS_BY_PRICE_RANGE_NO_CONTENT = "error.emptyproductlist.getproductsbypricerange";
    /**
     * Exception key for indicating that a product is unavailable while executing getProductById method.
     */
    public static final String GET_PRODUCT_BY_ID_NOT_FOUND = "error.productunavailable.getproductbyid";
    /**
     * Exception key for indicating that a product is unavailable while executing deleteProductById method.
     */
    public static final String DELETE_PRODUCT_BY_ID_NOT_FOUND = "error.productunavailable.deleteproductbyid";
    /**
     * Exception key for indicating that a product is unavailable while executing updateProduct method.
     */
    public static final String UPDATE_PRODUCT_NOT_FOUND = "error.productunavailable.updateproduct";
    /**
     * Exception key for indicating that a product is unavailable while executing updateProductFields method.
     */
    public static final String UPDATE_PRODUCT_FIELDS_NOT_FOUND = "error.productunavailable.updateproductfields";


}
