package com.microservices.productservice.service;

import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.exception.ProductServiceException;

import java.util.List;

/**
 * Service interface for managing products.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
public interface ProductService {

    /**
     * Retrieves all products.
     *
     * @return A list of ProductDto representing all products.
     * @throws ProductServiceException if the product list is empty.
     */
    List<ProductDto> getAllProducts() throws ProductServiceException;

    /**
     * Retrieves all products sorted by price.
     *
     * @return A list of ProductDto representing all products sorted by price.
     * @throws ProductServiceException if the product list is empty.
     */
    List<ProductDto> getAllProductsSortedByPrice() throws ProductServiceException;

    /**
     * Retrieves products within the specified price range.
     *
     * @param lowerLimit The lower limit of the price range.
     * @param upperLimit The upper limit of the price range.
     * @return A list of ProductDto within the given price range.
     * @throws ProductServiceException if the product list is empty.
     */
    List<ProductDto> getProductsByPriceRange(Double lowerLimit, Double upperLimit) throws ProductServiceException;

    /**
     * Creates a new product.
     *
     * @param productDto The Dto containing information for creating a new product.
     * @return The created ProductDto.
     */
    ProductDto createProduct(ProductDto productDto);

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return The ProductDto for the specified productId.
     * @throws ProductServiceException if the product is not found.
     */
    ProductDto getProductById(Long productId) throws ProductServiceException;

    /**
     * Deletes a product by its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return The deleted ProductDto.
     * @throws ProductServiceException if the product is not found.
     */
    ProductDto deleteProductById(Long productId) throws ProductServiceException;

    /**
     * Updates a product by its unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return The updated ProductResponseDto.
     * @throws ProductServiceException if the product is not found.
     */
    ProductDto updateProduct(ProductDto productDto) throws ProductServiceException;

    /**
     * Partially updates specific fields of a product identified by the given unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return The updated ProductDto.
     * @throws ProductServiceException if the product is not found.
     */
    ProductDto updateProductFields(ProductDto productDto) throws ProductServiceException;
}

