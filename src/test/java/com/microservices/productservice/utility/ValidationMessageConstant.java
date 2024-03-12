package com.microservices.productservice.utility;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ValidationMessageConstant {

    public static final String PRODUCT_NAME_NOT_BLANK_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productname.notblank");
    public static final String PRODUCT_NAME_ALPHA_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productname.alpha");
    public static final String PRODUCT_NAME_MIN_SIZE_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productname.minimumsize");
    public static final String PRODUCT_NAME_MAX_SIZE_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productname.maximumsize");
    public static final String PRODUCT_PRICE_MIN_VALUE_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productprice.minimumvalue");
    public static final String PRODUCT_PRICE_MAX_VALUE_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productprice.maximumvalue");
    public static final String PRODUCT_PRICE_PRICE_PATTERN_MESSAGE = PropertiesFileReader
            .getProperties("classpath:messages.properties")
            .getProperty("validation.productprice.pricepattern");

}
