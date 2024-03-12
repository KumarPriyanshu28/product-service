package com.microservices.productservice.utility;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ErrorCodeConstant {

    public static final Integer GET_ALL_PRODUCTS_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.emptyproductlist.getallproducts"));
    public static final Integer GET_ALL_PRODUCTS_SORTED_BY_PRICE_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.emptyproductlist.getallproductssortedbyprice"));
    public static final Integer GET_PRODUCTS_BY_PRICE_RANGE_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.emptyproductlist.getproductsbypricerange"));
    public static final Integer GET_PRODUCT_BY_ID_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.productunavailable.getproductbyid"));
    public static final Integer DELETE_PRODUCT_BY_ID_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.productunavailable.deleteproductbyid"));
    public static final Integer UPDATE_PRODUCT_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.productunavailable.updateproduct"));
    public static final Integer UPDATE_PRODUCT_FIELDS_ERROR_CODE = Integer.parseInt(PropertiesFileReader
            .getProperties("classpath:errorcode.properties")
            .getProperty("error.productunavailable.updateproductfields"));

}
