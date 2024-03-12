package com.microservices.productservice.dto;

import com.microservices.productservice.dto.annotation.PricePattern;
import com.microservices.productservice.dto.group.OnCreate;
import com.microservices.productservice.dto.group.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.microservices.productservice.utility.ValidationConstant.*;

/**
 * Data Transfer Object (DTO) representing product details.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    /**
     * Unique identifier of the product.
     */
    private long productId;

    /**
     * Name of the product.
     */
    @NotBlank(groups = {OnCreate.class}, message = PRODUCT_NAME_NOT_BLANK)
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "^[a-zA-Z ]*$", message = PRODUCT_NAME_ALPHA)
    @Size(groups = {OnCreate.class}, min=2, message = PRODUCT_NAME_MIN_SIZE)
    @Size(groups = {OnCreate.class, OnUpdate.class}, max=100, message = PRODUCT_NAME_MAX_SIZE)
    private String productName;
    /**
     * Price of the product.
     */
    @Min(groups = {OnCreate.class}, value = 100, message = PRODUCT_PRICE_MIN_VALUE)
    @Max(groups = {OnCreate.class, OnUpdate.class}, value = 100000, message = PRODUCT_PRICE_MAX_VALUE)
    @PricePattern(groups = {OnCreate.class, OnUpdate.class}, message = PRODUCT_PRICE_PRICE_PATTERN)
    private double productPrice;
}
