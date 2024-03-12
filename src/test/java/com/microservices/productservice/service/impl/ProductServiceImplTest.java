package com.microservices.productservice.service.impl;

import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.exception.ProductServiceException;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.microservices.productservice.utility.ProductDetailsConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("GET ALL PRODUCTS - SUCCESS")
    void GetAllProducts_ReturnProductList() {
        when(productRepository.findAll()).thenReturn(productList);
        when(productMapper.productListToProductDtoList(productList)).thenReturn(expectedProductListDto);

        List<ProductDto> actualProductList = productService.getAllProducts();

        assertNotNull(actualProductList);
        assertIterableEquals(expectedProductListDto, actualProductList);
        verifyProductRepoMethodCalledOnce("getAllProducts");
    }

    @Test
    @DisplayName("GET ALL PRODUCTS SORTED BY PRICE - SUCCESS")
    void GetAllProductsSortedByPrice_ReturnProductList(){
        when(productRepository.findAllSortedByPrice()).thenReturn(productList);
        when(productMapper.productListToProductDtoList(productList)).thenReturn(expectedProductListDto);

        List<ProductDto> actualProductList = productService.getAllProductsSortedByPrice();

        assertNotNull(actualProductList);
        assertIterableEquals(expectedProductListDto, actualProductList);
        verifyProductRepoMethodCalledOnce("getAllProductsSortedByPrice");
    }

    @Test
    @DisplayName("GET PRODUCTS IN SPECIFIED PRICE RANGE - SUCCESS")
    void GetProductsByPriceRange_ReturnProductList(){
        when(productRepository.findByPriceRange(PRODUCT_PRICE_LOWER_LIMIT, PRODUCT_PRICE_UPPER_LIMIT)).thenReturn(productList);
        when(productMapper.productListToProductDtoList(productList)).thenReturn(expectedProductListDto);

        List<ProductDto> actualProductList = productService.getProductsByPriceRange(PRODUCT_PRICE_LOWER_LIMIT, PRODUCT_PRICE_UPPER_LIMIT);

        assertNotNull(actualProductList);
        assertIterableEquals(expectedProductListDto, actualProductList);
        verifyProductRepoMethodCalledOnce("getProductsByPriceRange");
    }

    @Test
    @DisplayName("CREATE PRODUCT - SUCCESS")
    void CreateProduct_ReturnCreatedProduct() {
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setProductId(PRODUCT_ID_1);
            return product;
        });
        when(productMapper.productDtoToProduct(productDto)).thenReturn(product);
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto actualProductResponseDto = productService.createProduct(productDto);

        assertNotNull(actualProductResponseDto);
        assertEquals(expectedProductDto, actualProductResponseDto);
        verifyProductRepoMethodCalledOnce("createProduct");
    }

    @Test
    @DisplayName("GET PRODUCT BY ID - SUCCESS")
    void GetProductById_ReturnFetchedProduct() {
        when(productRepository.findById(PRODUCT_ID_1)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto actualProductResponseDto = productService.getProductById(PRODUCT_ID_1);

        assertNotNull(actualProductResponseDto);
        assertEquals(expectedProductDto, actualProductResponseDto);
        verifyProductRepoMethodCalledOnce("getProductById");
    }

    @Test
    @DisplayName("DELETE PRODUCT BY ID - SUCCESS")
    void DeleteProductById_ReturnDeletedProduct() {
        when(productRepository.findById(PRODUCT_ID_1)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(PRODUCT_ID_1);
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto actualProductResponseDto = productService.deleteProductById(PRODUCT_ID_1);

        assertNotNull(actualProductResponseDto);
        assertEquals(expectedProductDto, actualProductResponseDto);
        verifyProductRepoMethodCalledOnce("deleteProductById");
    }

    @Test
    @DisplayName("UPDATE PRODUCT - SUCCESS")
    void UpdateProduct_ReturnUpdatedProduct() {
        when(productMapper.productDtoToProduct(updatedProductDto)).thenReturn(updatedProduct);
        when(productRepository.findById(PRODUCT_ID_1)).thenReturn(Optional.of(updatedProduct));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);
        when(productMapper.productToProductDto(updatedProduct)).thenReturn(updatedProductDto);

        ProductDto actualProductResponseDto = productService.updateProduct(updatedProductDto);

        assertNotNull(actualProductResponseDto);
        assertEquals(updatedProductDto, actualProductResponseDto);
        verifyProductRepoMethodCalledOnce("updateProduct");
    }

    @Test
    @DisplayName("UPDATE PRODUCT FIELDS - SUCCESS")
    void UpdateProductFields_ReturnUpdatedProduct() {
        when(productRepository.findById(PRODUCT_ID_1)).thenReturn(Optional.of(product));
        when(productMapper.convertProductDtoToProduct(product, updatedProductDto)).thenReturn(updatedProduct);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);
        when(productMapper.productToProductDto(updatedProduct)).thenReturn(updatedProductDto);

        ProductDto actualProductResponseDto = productService.updateProductFields(updatedProductDto);

        assertNotNull(actualProductResponseDto);
        assertEquals(updatedProductDto, actualProductResponseDto);
        verifyProductRepoMethodCalledOnce("updateProductFields");
    }

    @ParameterizedTest
    @ValueSource(strings = {"getAllProducts",
                            "getAllProductsSortedByPrice",
                            "getProductsByPriceRange"})
    @DisplayName("GET PRODUCTS - SINGLE PRODUCT IN LIST")
    void GetProducts_ReturnSingleProductInList(String methodName) {
        when(invokeRepoMethodWithProductList(methodName)).thenReturn(singletonProductList);
        when(productMapper.productListToProductDtoList(singletonProductList)).thenReturn(singletonProductListDto);

        List<ProductDto> actualProductList = invokeServiceMethodWithProductList(methodName);

        assertNotNull(actualProductList);
        assertIterableEquals(singletonProductListDto, actualProductList);
        verifyProductRepoMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getAllProducts",
                            "getAllProductsSortedByPrice",
                            "getProductsByPriceRange"})
    @DisplayName("GET PRODUCTS - EMPTY PRODUCT LIST")
    void GetProducts_EmptyProductList_ExceptionThrown(String methodName) {
        when(invokeRepoMethodWithProductList(methodName)).thenReturn(Collections.emptyList());
        assertThrows(ProductServiceException.class, () -> invokeServiceMethodWithProductList(methodName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getProductById",
                            "deleteProductById",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("GET PRODUCT - INVALID INPUT")
    void GetProduct_InvalidInput_ExceptionThrown(String methodName) {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProductServiceException.class, () -> invokeMethodWithInvalidInput(methodName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"getProductById",
                            "deleteProductById",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("GET PRODUCT - PRODUCT NOT FOUND")
    void GetProduct_NonExistentProduct_ExceptionThrown(String methodName) {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProductServiceException.class, () -> invokeMethodWithProductNotFound(methodName));
    }

    private List<Product> invokeRepoMethodWithProductList(String methodName) {
        return switch (methodName) {
            case "getAllProducts" -> productRepository.findAll();
            case "getAllProductsSortedByPrice" -> productRepository.findAllSortedByPrice();
            case "getProductsByPriceRange" -> productRepository.findByPriceRange(PRODUCT_PRICE_LOWER_LIMIT,
                    PRODUCT_PRICE_UPPER_LIMIT);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private List<ProductDto> invokeServiceMethodWithProductList(String methodName) {
        return switch (methodName) {
            case "getAllProducts" -> productService.getAllProducts();
            case "getAllProductsSortedByPrice" -> productService.getAllProductsSortedByPrice();
            case "getProductsByPriceRange" -> productService.getProductsByPriceRange(PRODUCT_PRICE_LOWER_LIMIT,
                    PRODUCT_PRICE_UPPER_LIMIT);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private void invokeMethodWithInvalidInput(String methodName) {
        switch (methodName) {
            case "getProductById" -> productService.getProductById(INVALID_PRODUCT_ID);
            case "deleteProductById" -> productService.deleteProductById(INVALID_PRODUCT_ID);
            case "updateProduct" -> productService.updateProduct(invalidProductDto);
            case "updateProductFields" -> productService.updateProductFields(invalidProductDto);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        }
    }

    private void invokeMethodWithProductNotFound(String methodName) {
        switch (methodName) {
            case "getProductById" -> productService.getProductById(NON_EXISTENT_PRODUCT_ID);
            case "deleteProductById" -> productService.deleteProductById(NON_EXISTENT_PRODUCT_ID);
            case "updateProduct" -> productService.updateProduct(nonExistentProductDto);
            case "updateProductFields" -> productService.updateProductFields(nonExistentProductDto);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        }
    }

    private void verifyProductRepoMethodCalledOnce(String methodName) {
        switch (methodName) {
            case "getAllProducts" ->
                    verify(productRepository, times(1)).findAll();
            case "getAllProductsSortedByPrice" ->
                    verify(productRepository, times(1)).findAllSortedByPrice();
            case "getProductsByPriceRange" ->
                    verify(productRepository, times(1)).findByPriceRange(anyDouble(),
                            anyDouble());
            case "createProduct", "updateProduct", "updateProductFields" ->
                    verify(productRepository, times(1)).save(any(Product.class));
            case "getProductById" ->
                    verify(productRepository, times(1)).findById(anyLong());
            case "deleteProductById" ->
                    verify(productRepository, times(1)).deleteById(anyLong());
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        }
    }

}
