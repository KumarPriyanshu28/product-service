package com.microservices.productservice.utility;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ErrorMessageConstant {

    public static final String GET_ALL_PRODUCTS_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.emptyproductlist.getallproducts");
    public static final String GET_ALL_PRODUCTS_SORTED_BY_PRICE_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.emptyproductlist.getallproductssortedbyprice");
    public static final String GET_PRODUCTS_BY_PRICE_RANGE_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.emptyproductlist.getproductsbypricerange");
    public static final String GET_PRODUCT_BY_ID_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.productunavailable.getproductbyid");
    public static final String DELETE_PRODUCT_BY_ID_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.productunavailable.deleteproductbyid");
    public static final String UPDATE_PRODUCT_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.productunavailable.updateproduct");
    public static final String UPDATE_PRODUCT_FIELDS_ERROR_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("error.productunavailable.updateproductfields");


}
