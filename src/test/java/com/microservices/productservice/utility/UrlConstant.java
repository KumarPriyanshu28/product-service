package com.microservices.productservice.utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;


@Configuration
public class UrlConstant {
    private static final Object obj;

    static {
        try {
            obj = new JSONParser().parse(new FileReader("src/test/resources/constant.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final JSONObject jsonObject = (JSONObject) obj;
    public static final String GENERIC_PRODUCTS_URL = (String) jsonObject.get("genericProductsUrl");
    public static final String SORTED_PRODUCTS_URL = (String) jsonObject.get("sortedProductsUrl");
    public static final String RANGED_PRODUCTS_URL = (String) jsonObject.get("rangedProductsUrl");
    public static final String SPECIFIC_PRODUCT_URL = (String) jsonObject.get("specificProductUrl");

}
