package com.microservices.productservice.service.impl;

import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.exception.ProductServiceException;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductService;
import com.microservices.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.microservices.productservice.utility.ExceptionConstant.*;

/**
 * Implementation of the {@link ProductService} interface.
 * Provides business logic for managing products.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Retrieves all products.
     *
     * @return A list of ProductDto representing all products.
     */
    @Override
    public List<ProductDto> getAllProducts() {
        log.debug("Entering in ProductServiceImpl : getAllProducts()");
        log.info("Getting all products");
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) {
            log.error(GET_ALL_PRODUCTS_NO_CONTENT);
            throw new ProductServiceException(GET_ALL_PRODUCTS_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        log.debug("Exiting from ProductServiceImpl : getAllProducts()");
        return productMapper.productListToProductDtoList(productList);
    }

    /**
     * Retrieves all products sorted by price.
     *
     * @return A list of ProductDto representing all products sorted by price.
     */
    @Override
    public List<ProductDto> getAllProductsSortedByPrice() {
        log.debug("Entering in ProductServiceImpl : getAllProductsSortedByPrice()");
        log.info("Getting all products sorted by price");
        List<Product> productList = productRepository.findAllSortedByPrice();
        if (productList.isEmpty()) {
            log.error(GET_ALL_PRODUCTS_SORTED_BY_PRICE_NO_CONTENT);
            throw new ProductServiceException(GET_ALL_PRODUCTS_SORTED_BY_PRICE_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        log.debug("Exiting from ProductServiceImpl : getAllProductsSortedByPrice()");
        return productMapper.productListToProductDtoList(productList);
    }

    /**
     * Retrieves products within the specified price range.
     *
     * @param lowerLimit The lower limit of the price range.
     * @param upperLimit The upper limit of the price range.
     * @return A list of ProductDto within the given price range.
     */
    @Override
    public List<ProductDto> getProductsByPriceRange(Double lowerLimit, Double upperLimit) {
        log.debug("Entering in ProductServiceImpl : getProductsByPriceRange()");
        log.info("Getting products by price range: {} - {}", lowerLimit, upperLimit);
        List<Product> productList = productRepository.findByPriceRange(lowerLimit, upperLimit);
        if (productList.isEmpty()) {
            log.error(GET_PRODUCTS_BY_PRICE_RANGE_NO_CONTENT);
            throw new ProductServiceException(GET_PRODUCTS_BY_PRICE_RANGE_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        log.debug("Exiting from ProductServiceImpl : getProductsByPriceRange()");
        return productMapper.productListToProductDtoList(productList);
    }

    /**
     * Creates a new product.
     *
     * @param productDto The Dto containing information for creating a new product.
     * @return The created ProductDto.
     */
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.debug("Entering in ProductServiceImpl : createProduct()");
        log.info("Creating product: {}", productDto);
        Product product = productMapper.productDtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        log.debug("Exiting from ProductServiceImpl : createProduct()");
        return productMapper.productToProductDto(savedProduct);
    }

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return The ProductDto for the specified productId.
     * @throws ProductServiceException if the product is not found.
     */
    @Override
    public ProductDto getProductById(Long productId) {
        log.debug("Entering in ProductServiceImpl : getProductById()");
        log.info("Getting product by id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(GET_PRODUCT_BY_ID_NOT_FOUND, HttpStatus.NOT_FOUND));
        log.debug("Exiting from ProductServiceImpl : getProductById()");
        return productMapper.productToProductDto(product);
    }

    /**
     * Deletes a product by its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return The deleted ProductDto.
     * @throws ProductServiceException if the product is not found.
     */
    @Override
    public ProductDto deleteProductById(Long productId) {
        log.debug("Entering in ProductServiceImpl : deleteProductById()");
        log.info("Deleting product by id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(DELETE_PRODUCT_BY_ID_NOT_FOUND, HttpStatus.NOT_FOUND));
        productRepository.deleteById(productId);
        log.debug("Exiting from ProductServiceImpl : deleteProductById()");
        return productMapper.productToProductDto(product);
    }

    /**
     * Updates a product by its unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return The updated ProductResponseDto.
     */
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        log.debug("Entering in ProductServiceImpl : updateProduct()");
        log.info("Updating product: {}", productDto);
        Product existingProduct = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new ProductServiceException(UPDATE_PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND));
        Product product = productMapper.productDtoToProduct(productDto);
        product.setProductId(existingProduct.getProductId());

        Product updatedProduct = productRepository.save(product);

        log.debug("Exiting from ProductServiceImpl : updateProduct()");
        return productMapper.productToProductDto(updatedProduct);
    }

    /**
     * Partially updates specific fields of a product identified by the given unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return The updated ProductDto.
     * @throws ProductServiceException if the product is not found.
     */
    @Override
    public ProductDto updateProductFields(ProductDto productDto) {
        log.debug("Entering in ProductServiceImpl : updateProductFields()");
        log.info("Updating product fields: {}", productDto);

        Product existingProduct = productRepository.findById(productDto.getProductId())
                .orElseThrow(() -> new ProductServiceException(UPDATE_PRODUCT_FIELDS_NOT_FOUND, HttpStatus.NOT_FOUND));

        Product product = productMapper.convertProductDtoToProduct(existingProduct, productDto);
        Product updatedProduct = productRepository.save(product);

        log.debug("Exiting from ProductServiceImpl : updateProductFields()");
        return productMapper.productToProductDto(updatedProduct);
    }
}
