package com.microservices.productservice.utility;

import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.entity.Product;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
public class ProductDetailsConstant {
    private static final Object obj;

    static {
        try {
            obj = new JSONParser().parse(new FileReader("src/test/resources/constant.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final JSONObject jsonObject = (JSONObject) obj;
    public static final Long PRODUCT_ID_1 = (Long) jsonObject.get("productIdOne");
    public static final String PRODUCT_NAME_1 =  (String) jsonObject.get("productNameOne");
    public static final Double PRODUCT_PRICE_1=(Double) jsonObject.get("productPriceOne");
    public static final Long PRODUCT_ID_2=(Long) jsonObject.get("productIdTwo");
    public static final String PRODUCT_NAME_2= (String) jsonObject.get("productNameTwo");
    public static final Double PRODUCT_PRICE_2=(Double) jsonObject.get("productPriceTwo");
    public static final Long INVALID_PRODUCT_ID=(Long) jsonObject.get("productIdInvalid");
    public static final Long NON_EXISTENT_PRODUCT_ID=(Long) jsonObject.get("productIdNonExistent");
    public static final Double PRODUCT_PRICE_LOWER_LIMIT=(Double) jsonObject.get("productPriceLowerLimit");
    public static final Double PRODUCT_PRICE_UPPER_LIMIT=(Double) jsonObject.get("productPriceUpperLimit");
    public static final String UPDATED_PRODUCT_NAME_1= (String) jsonObject.get("productNameOneUpdated");
    public static final Double UPDATED_PRODUCT_PRICE_1=(Double) jsonObject.get("productPriceOneUpdated");
    public static final String BLANK_PRODUCT_NAME= (String) jsonObject.get("productNameBlank");
    public static final String INVALID_PRODUCT_NAME= (String) jsonObject.get("productNameInvalid");
    public static final String MINIMUM_SIZE_PRODUCT_NAME= (String) jsonObject.get("productNameMinimumSize");
    public static final Double MINIMUM_PRODUCT_PRICE=(Double) jsonObject.get("productPriceMinimum");
    public static final Double MAXIMUM_PRODUCT_PRICE=(Double) jsonObject.get("productPriceMaximum");
    public static final Double INVALID_PRODUCT_PRICE=(Double) jsonObject.get("productPriceInvalid");

    public static final Product product = new Product(PRODUCT_ID_1, PRODUCT_NAME_1, PRODUCT_PRICE_1);
    public static final Product updatedProduct = new Product(PRODUCT_ID_1, UPDATED_PRODUCT_NAME_1, UPDATED_PRODUCT_PRICE_1);
    public static final List<Product> productList = List.of(
                                                new Product(PRODUCT_ID_1, PRODUCT_NAME_1, PRODUCT_PRICE_1),
                                                new Product(PRODUCT_ID_2, PRODUCT_NAME_2, PRODUCT_PRICE_2));
    public static final List<Product> singletonProductList = Collections.singletonList(product);
    public static final ProductDto productDto = new ProductDto(PRODUCT_ID_1, PRODUCT_NAME_1, PRODUCT_PRICE_1);
    public static final ProductDto expectedProductDto = new ProductDto(PRODUCT_ID_1, PRODUCT_NAME_1, PRODUCT_PRICE_1);
    public static final ProductDto updatedProductDto = new ProductDto(PRODUCT_ID_1, UPDATED_PRODUCT_NAME_1, UPDATED_PRODUCT_PRICE_1);
    public static final ProductDto updatedProductFieldsDto = new ProductDto(PRODUCT_ID_1, UPDATED_PRODUCT_NAME_1, PRODUCT_PRICE_1);
    public static final ProductDto invalidProductDto = new ProductDto(INVALID_PRODUCT_ID, UPDATED_PRODUCT_NAME_1, UPDATED_PRODUCT_PRICE_1);
    public static final ProductDto nonExistentProductDto = new ProductDto(NON_EXISTENT_PRODUCT_ID, UPDATED_PRODUCT_NAME_1, UPDATED_PRODUCT_PRICE_1);
    public static final List<ProductDto> expectedProductListDto = List.of(
                                                new ProductDto(PRODUCT_ID_1, PRODUCT_NAME_1, PRODUCT_PRICE_1),
                                                new ProductDto(PRODUCT_ID_2, PRODUCT_NAME_2, PRODUCT_PRICE_2));
    public static final List<ProductDto> singletonProductListDto = Collections.singletonList(productDto);
}
