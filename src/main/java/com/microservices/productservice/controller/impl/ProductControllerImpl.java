package com.microservices.productservice.controller.impl;

import com.microservices.productservice.controller.ProductController;
import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of the {@link ProductController} interface.
 * Handles HTTP requests related to product management.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@AllArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return A ResponseEntity containing a list of ProductDto.
     */
    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    /**
     * Retrieves all products sorted by price.
     *
     * @return A ResponseEntity containing a sorted list of ProductDto.
     */
    @Override
    public ResponseEntity<List<ProductDto>> getAllProductsSortedByPrice() {
        return new ResponseEntity<>(productService.getAllProductsSortedByPrice(), HttpStatus.OK);
    }

    /**
     * Retrieves products within the specified price range.
     *
     * @param lowerLimit The lower limit of the price range.
     * @param upperLimit The upper limit of the price range.
     * @return A ResponseEntity containing a list of ProductDto within the given price range.
     */
    @Override
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(Double lowerLimit, Double upperLimit) {
        return new ResponseEntity<>(productService.getProductsByPriceRange(lowerLimit, upperLimit), HttpStatus.OK);
    }

    /**
     * Creates a new product.
     *
     * @param productDto The Dto containing information for creating a new product.
     * @return A ResponseEntity containing the created ProductDto.
     */
    @Override
    public ResponseEntity<ProductDto> createProduct(ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return A ResponseEntity containing the ProductDto for the specified productId.
     */
    @Override
    public ResponseEntity<ProductDto> getProductById(Long productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return A ResponseEntity containing the deleted ProductDto.
     */
    @Override
    public ResponseEntity<ProductDto> deleteProductById(Long productId) {
        return new ResponseEntity<>(productService.deleteProductById(productId), HttpStatus.OK);
    }

    /**
     * Updates a product by its unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return A ResponseEntity containing the updated ProductResponseDto.
     */
    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProduct(productDto), HttpStatus.OK);
    }

    /**
     * Partially updates product fields of a product identified by the given unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return A ResponseEntity containing the updated ProductDto.
     */
    @Override
    public ResponseEntity<ProductDto> updateProductFields(ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProductFields(productDto), HttpStatus.OK);
    }

}
