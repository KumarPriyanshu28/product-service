package com.microservices.productservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.exception.ProductServiceException;
import com.microservices.productservice.service.impl.ProductServiceImpl;
import com.microservices.productservice.utility.ErrorCodeConstant;
import com.microservices.productservice.utility.ProductDetailsConstant;
import com.microservices.productservice.utility.UrlConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static com.microservices.productservice.utility.ErrorMessageConstant.*;
import static com.microservices.productservice.utility.ExceptionConstant.*;
import static com.microservices.productservice.utility.ValidationMessageConstant.*;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerImplTest {
    @MockBean
    private ProductServiceImpl productService;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("GET ALL PRODUCTS - SUCCESS")
    void GetAllProducts_ReturnProductList() throws Exception {
        when(productService.getAllProducts()).thenReturn(ProductDetailsConstant.expectedProductListDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.expectedProductListDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("getAllProducts");
    }

    @Test
    @DisplayName("GET ALL PRODUCTS SORTED BY PRICE - SUCCESS")
    void GetAllProductsSortedByPrice_ReturnProductList() throws Exception {
        when(productService.getAllProductsSortedByPrice()).thenReturn(ProductDetailsConstant.expectedProductListDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(UrlConstant.SORTED_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.expectedProductListDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("getAllProductsSortedByPrice");

    }

    @Test
    @DisplayName("GET PRODUCTS IN SPECIFIED PRICE RANGE - SUCCESS")
    void GetProductsByPriceRange_ReturnProductList() throws Exception {
        when(productService.getProductsByPriceRange(ProductDetailsConstant.PRODUCT_PRICE_LOWER_LIMIT, ProductDetailsConstant.PRODUCT_PRICE_UPPER_LIMIT)).thenReturn(ProductDetailsConstant.expectedProductListDto);

        RequestBuilder requestBuilder = get(UrlConstant.RANGED_PRODUCTS_URL, ProductDetailsConstant.PRODUCT_PRICE_LOWER_LIMIT, ProductDetailsConstant.PRODUCT_PRICE_UPPER_LIMIT).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.expectedProductListDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("getProductsByPriceRange");
    }

    @Test
    @DisplayName("CREATE PRODUCT - SUCCESS")
    void CreateProduct_ReturnCreatedProduct() throws Exception {
        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.productDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                .content(expectedResponse)
                .contentType(MediaType.APPLICATION_JSON);

        when(productService.createProduct(ProductDetailsConstant.productDto)).thenReturn(ProductDetailsConstant.expectedProductDto);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("createProduct");
    }


    @Test
    @DisplayName("GET PRODUCT BY ID - SUCCESS")
    void GetProductById_ReturnFetchedProduct() throws Exception {
        RequestBuilder requestBuilder = get(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);

        when(productService.getProductById(ProductDetailsConstant.PRODUCT_ID_1)).thenReturn(ProductDetailsConstant.expectedProductDto);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.expectedProductDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("getProductById");
    }

    @Test
    @DisplayName("DELETE PRODUCT BY ID - SUCCESS")
    void DeleteProductById_ReturnDeletedProduct() throws Exception {
        RequestBuilder requestBuilder = delete(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);

        when(productService.deleteProductById(ProductDetailsConstant.PRODUCT_ID_1)).thenReturn(ProductDetailsConstant.expectedProductDto);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.expectedProductDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("deleteProductById");
    }

    @Test
    @DisplayName("UPDATE PRODUCT - SUCCESS")
    void UpdateProduct_ReturnUpdatedProduct() throws Exception {
        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.updatedProductDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                .content(expectedResponse)
                .contentType(MediaType.APPLICATION_JSON);

        when(productService.updateProduct(any(ProductDto.class))).thenReturn(ProductDetailsConstant.updatedProductDto);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("updateProduct");
    }

    @Test
    @DisplayName("UPDATE PRODUCT FIELDS - SUCCESS")
    void UpdateProductFields_ReturnUpdatedProduct() throws Exception {
        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.updatedProductFieldsDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                .content(expectedResponse)
                .contentType(MediaType.APPLICATION_JSON);

        when(productService.updateProductFields(any(ProductDto.class))).thenReturn(ProductDetailsConstant.updatedProductFieldsDto);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce("updateProductFields");
    }

    @ParameterizedTest
    @ValueSource(strings = {"getAllProducts",
                            "getAllProductsSortedByPrice",
                            "getProductsByPriceRange"})
    @DisplayName("GET PRODUCTS - SINGLE PRODUCT IN LIST")
    void GetProducts_ReturnSingleProductInList(String methodName) throws Exception {
        when(invokeMethodWithProductList(methodName)).thenReturn(ProductDetailsConstant.singletonProductListDto);

        RequestBuilder requestBuilder = getRequestBuilderForMethod(methodName);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

        String expectedResponse = objectMapper.writeValueAsString(ProductDetailsConstant.singletonProductListDto);
        String actualResponse = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
        verifyProductServiceMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getAllProducts",
                            "getAllProductsSortedByPrice",
                            "getProductsByPriceRange"})
    @DisplayName("GET PRODUCTS - EMPTY PRODUCT LIST")
    void GetProducts_EmptyProductList_ExceptionThrown(String methodName) throws Exception {
        RequestBuilder requestBuilder = getRequestBuilderForMethod(methodName);
        when(invokeMethodWithException(methodName))
                .thenThrow(new ProductServiceException(getSpecificMethodErrorMessage(methodName), HttpStatus.NO_CONTENT));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.statusCode").value(getSpecificMethodExpectedErrorCode(methodName)))
                .andExpect(jsonPath("$.message").value(getSpecificMethodExpectedErrorMessage(methodName)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        verifyProductServiceMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getProductById",
                            "deleteProductById",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("GET PRODUCT - INVALID INPUT")
    void GetProduct_InvalidInput_ExceptionThrown(String methodName) throws Exception {
        RequestBuilder requestBuilder = getRequestBuilderForInvalidInput(methodName);
        when(invokeMethodWithInvalidInput(methodName))
                .thenThrow(new ProductServiceException(getSpecificMethodErrorMessage(methodName), HttpStatus.NOT_FOUND));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(getSpecificMethodExpectedErrorCode(methodName)))
                .andExpect(jsonPath("$.message").value(getSpecificMethodExpectedErrorMessage(methodName)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        verifyProductServiceMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getProductById",
                            "deleteProductById",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("GET PRODUCT - PRODUCT NOT FOUND")
    void GetProduct_NonExistentProduct_ExceptionThrown(String methodName) throws Exception {
        RequestBuilder requestBuilder = getRequestBuilderForProductNotFound(methodName);
        when(invokeMethodWithProductNotFound(methodName))
                .thenThrow(new ProductServiceException(getSpecificMethodErrorMessage(methodName), HttpStatus.NOT_FOUND));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(getSpecificMethodExpectedErrorCode(methodName)))
                .andExpect(jsonPath("$.message").value(getSpecificMethodExpectedErrorMessage(methodName)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        verifyProductServiceMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"getAllProducts",
                            "getAllProductsSortedByPrice",
                            "getProductsByPriceRange",
                            "createProduct",
                            "getProductById",
                            "deleteProductById",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("EXCEPTION HANDLING FOR ALL METHODS - GENERIC EXCEPTION")
    void AllMethods_ExceptionOccurred_GenericExceptionThrown(String methodName) throws Exception {
        RequestBuilder requestBuilder = getRequestBuilderForMethod(methodName);
        when(invokeMethodWithException(methodName))
                .thenThrow(new RuntimeException("Some exception occurred."));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        verifyProductServiceMethodCalledOnce(methodName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct"})
    @DisplayName("VALIDATE PRODUCT - PRODUCT NAME BLANK")
    void ValidateProduct_ProductNameBlank_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.BLANK_PRODUCT_NAME, ProductDetailsConstant.PRODUCT_PRICE_1);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].message", hasItem(PRODUCT_NAME_NOT_BLANK_MESSAGE)))
                .andExpect(jsonPath("$[*].message", hasItem(PRODUCT_NAME_MIN_SIZE_MESSAGE)))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("VALIDATE PRODUCT - INVALID PRODUCT NAME PATTERN")
    void ValidateProduct_InvalidProductNamePattern_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.INVALID_PRODUCT_NAME, ProductDetailsConstant.PRODUCT_PRICE_1);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_NAME_ALPHA_MESSAGE))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct"})
    @DisplayName("VALIDATE PRODUCT - PRODUCT NAME MIN SIZE")
    void ValidateProduct_ProductNameMinSize_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.MINIMUM_SIZE_PRODUCT_NAME, ProductDetailsConstant.PRODUCT_PRICE_1);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_NAME_MIN_SIZE_MESSAGE))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("VALIDATE PRODUCT - PRODUCT NAME MAX SIZE")
    void ValidateProduct_ProductNameMaxSize_ReturnBadRequest(String methodName) throws Exception {
        String productName = String.join("", Collections.nCopies(101, ProductDetailsConstant.PRODUCT_NAME_1));
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, productName, ProductDetailsConstant.PRODUCT_PRICE_1);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_NAME_MAX_SIZE_MESSAGE))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct"})
    @DisplayName("VALIDATE PRODUCT - PRODUCT PRICE MIN VALUE")
    void ValidateProduct_ProductPriceMinValue_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.PRODUCT_NAME_1, ProductDetailsConstant.MINIMUM_PRODUCT_PRICE);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_PRICE_MIN_VALUE_MESSAGE))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("VALIDATE PRODUCT - PRODUCT PRICE MAX VALUE")
    void ValidateProduct_ProductPriceMaxValue_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.PRODUCT_NAME_1, ProductDetailsConstant.MAXIMUM_PRODUCT_PRICE);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_PRICE_MAX_VALUE_MESSAGE))
                .andReturn();
    }

    @ParameterizedTest
    @ValueSource(strings = {"createProduct",
                            "updateProduct",
                            "updateProductFields"})
    @DisplayName("VALIDATE PRODUCT - INVALID PRODUCT PRICE PATTERN")
    void ValidateProduct_InvalidProductPricePattern_ReturnBadRequest(String methodName) throws Exception {
        ProductDto productDto = new ProductDto(ProductDetailsConstant.PRODUCT_ID_1, ProductDetailsConstant.PRODUCT_NAME_1, ProductDetailsConstant.INVALID_PRODUCT_PRICE);
        RequestBuilder requestBuilder = getRequestBuilderForProductValidation(methodName, productDto);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(PRODUCT_PRICE_PRICE_PATTERN_MESSAGE))
                .andReturn();

    }

    private List<ProductDto> invokeMethodWithProductList(String methodName) {
        return switch (methodName) {
            case "getAllProducts" ->
                    productService.getAllProducts();
            case "getAllProductsSortedByPrice" ->
                    productService.getAllProductsSortedByPrice();
            case "getProductsByPriceRange" ->
                    productService.getProductsByPriceRange(ProductDetailsConstant.PRODUCT_PRICE_LOWER_LIMIT,
                                                           ProductDetailsConstant.PRODUCT_PRICE_UPPER_LIMIT);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private ProductDto invokeMethodWithInvalidInput(String methodName) {
        return switch (methodName) {
            case "getProductById" -> productService.getProductById(ProductDetailsConstant.INVALID_PRODUCT_ID);
            case "deleteProductById" -> productService.deleteProductById(ProductDetailsConstant.INVALID_PRODUCT_ID);
            case "updateProduct" -> productService.updateProduct(ProductDetailsConstant.invalidProductDto);
            case "updateProductFields" -> productService.updateProductFields(ProductDetailsConstant.invalidProductDto);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private ProductDto invokeMethodWithProductNotFound(String methodName) {
        return switch (methodName) {
            case "getProductById" -> productService.getProductById(ProductDetailsConstant.NON_EXISTENT_PRODUCT_ID);
            case "deleteProductById" -> productService.deleteProductById(ProductDetailsConstant.NON_EXISTENT_PRODUCT_ID);
            case "updateProduct" -> productService.updateProduct(ProductDetailsConstant.nonExistentProductDto);
            case "updateProductFields" -> productService.updateProductFields(ProductDetailsConstant.nonExistentProductDto);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private Exception invokeMethodWithException(String methodName) {
        switch (methodName) {
            case "getAllProducts" -> productService.getAllProducts();
            case "getAllProductsSortedByPrice" -> productService.getAllProductsSortedByPrice();
            case "getProductsByPriceRange" ->
                    productService.getProductsByPriceRange(ProductDetailsConstant.PRODUCT_PRICE_LOWER_LIMIT,
                            ProductDetailsConstant.PRODUCT_PRICE_UPPER_LIMIT);
            case "createProduct" -> productService.createProduct(ProductDetailsConstant.productDto);
            case "getProductById" -> productService.getProductById(ProductDetailsConstant.PRODUCT_ID_1);
            case "deleteProductById" -> productService.deleteProductById(ProductDetailsConstant.PRODUCT_ID_1);
            case "updateProduct" -> productService.updateProduct(ProductDetailsConstant.productDto);
            case "updateProductFields" -> productService.updateProductFields(ProductDetailsConstant.productDto);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        }
        return null;
    }

    private RequestBuilder getRequestBuilderForMethod(String methodName) throws JsonProcessingException {
        return switch (methodName) {
            case "getAllProducts" ->
                    MockMvcRequestBuilders.get(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON);
            case "getAllProductsSortedByPrice" ->
                    MockMvcRequestBuilders.get(UrlConstant.SORTED_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON);
            case "getProductsByPriceRange" ->
                    get(UrlConstant.RANGED_PRODUCTS_URL, ProductDetailsConstant.PRODUCT_PRICE_LOWER_LIMIT, ProductDetailsConstant.PRODUCT_PRICE_UPPER_LIMIT)
                            .accept(MediaType.APPLICATION_JSON);
            case "createProduct" ->
                    MockMvcRequestBuilders.post(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                                              .content(objectMapper.writeValueAsString(ProductDetailsConstant.productDto))
                                              .contentType(MediaType.APPLICATION_JSON);
            case "getProductById" ->
                    get(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "deleteProductById" ->
                    delete(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "updateProduct" ->
                MockMvcRequestBuilders.put(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ProductDetailsConstant.productDto))
                        .contentType(MediaType.APPLICATION_JSON);

            case "updateProductFields" ->
                    MockMvcRequestBuilders.patch(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ProductDetailsConstant.productDto))
                            .contentType(MediaType.APPLICATION_JSON);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private RequestBuilder getRequestBuilderForInvalidInput(String methodName) throws JsonProcessingException {
        return switch (methodName) {
            case "getProductById" ->
                    get(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "deleteProductById" ->
                    delete(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "updateProduct" ->
                    MockMvcRequestBuilders.put(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ProductDetailsConstant.invalidProductDto))
                            .contentType(MediaType.APPLICATION_JSON);

            case "updateProductFields" ->
                    MockMvcRequestBuilders.patch(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ProductDetailsConstant.invalidProductDto))
                            .contentType(MediaType.APPLICATION_JSON);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private RequestBuilder getRequestBuilderForProductNotFound(String methodName) throws JsonProcessingException {
        return switch (methodName) {
            case "getProductById" ->
                    get(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "deleteProductById" ->
                    delete(UrlConstant.SPECIFIC_PRODUCT_URL, anyLong()).accept(MediaType.APPLICATION_JSON);
            case "updateProduct" ->
                    MockMvcRequestBuilders.put(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ProductDetailsConstant.nonExistentProductDto))
                            .contentType(MediaType.APPLICATION_JSON);

            case "updateProductFields" ->
                    MockMvcRequestBuilders.patch(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ProductDetailsConstant.nonExistentProductDto))
                            .contentType(MediaType.APPLICATION_JSON);
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private RequestBuilder getRequestBuilderForProductValidation(String methodName, ProductDto productDto) throws JsonProcessingException {
        return switch (methodName) {
            case "createProduct" ->
                    MockMvcRequestBuilders.post(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productDto))
                            .contentType(MediaType.APPLICATION_JSON);
            case "updateProduct" ->
                    MockMvcRequestBuilders.put(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productDto))
                            .contentType(MediaType.APPLICATION_JSON);
            case "updateProductFields" ->
                    MockMvcRequestBuilders.patch(UrlConstant.GENERIC_PRODUCTS_URL).accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(productDto))
                            .contentType(MediaType.APPLICATION_JSON);

            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private String getSpecificMethodErrorMessage(String methodName) {
        return switch (methodName) {
            case "getAllProducts" -> GET_ALL_PRODUCTS_NO_CONTENT;
            case "getAllProductsSortedByPrice" -> GET_ALL_PRODUCTS_SORTED_BY_PRICE_NO_CONTENT;
            case "getProductsByPriceRange" -> GET_PRODUCTS_BY_PRICE_RANGE_NO_CONTENT;
            case "getProductById" -> GET_PRODUCT_BY_ID_NOT_FOUND;
            case "deleteProductById" -> DELETE_PRODUCT_BY_ID_NOT_FOUND;
            case "updateProduct" -> UPDATE_PRODUCT_NOT_FOUND;
            case "updateProductFields" -> UPDATE_PRODUCT_FIELDS_NOT_FOUND;
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private Integer getSpecificMethodExpectedErrorCode(String methodName) {
        return switch (methodName) {
            case "getAllProducts" -> ErrorCodeConstant.GET_ALL_PRODUCTS_ERROR_CODE;
            case "getAllProductsSortedByPrice" -> ErrorCodeConstant.GET_ALL_PRODUCTS_SORTED_BY_PRICE_ERROR_CODE;
            case "getProductsByPriceRange" -> ErrorCodeConstant.GET_PRODUCTS_BY_PRICE_RANGE_ERROR_CODE;
            case "getProductById" -> ErrorCodeConstant.GET_PRODUCT_BY_ID_ERROR_CODE;
            case "deleteProductById" -> ErrorCodeConstant.DELETE_PRODUCT_BY_ID_ERROR_CODE;
            case "updateProduct" -> ErrorCodeConstant.UPDATE_PRODUCT_ERROR_CODE;
            case "updateProductFields" -> ErrorCodeConstant.UPDATE_PRODUCT_FIELDS_ERROR_CODE;
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private String getSpecificMethodExpectedErrorMessage(String methodName) {
        return switch (methodName) {
            case "getAllProducts" -> GET_ALL_PRODUCTS_ERROR_MESSAGE;
            case "getAllProductsSortedByPrice" -> GET_ALL_PRODUCTS_SORTED_BY_PRICE_ERROR_MESSAGE;
            case "getProductsByPriceRange" -> GET_PRODUCTS_BY_PRICE_RANGE_ERROR_MESSAGE;
            case "getProductById" -> GET_PRODUCT_BY_ID_ERROR_MESSAGE;
            case "deleteProductById" -> DELETE_PRODUCT_BY_ID_ERROR_MESSAGE;
            case "updateProduct" -> UPDATE_PRODUCT_ERROR_MESSAGE;
            case "updateProductFields" -> UPDATE_PRODUCT_FIELDS_ERROR_MESSAGE;
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        };
    }

    private void verifyProductServiceMethodCalledOnce(String methodName) {
        switch (methodName) {
            case "getAllProducts" ->
                    verify(productService, times(1)).getAllProducts();
            case "getAllProductsSortedByPrice" ->
                    verify(productService, times(1)).getAllProductsSortedByPrice();
            case "getProductsByPriceRange" ->
                    verify(productService, times(1)).getProductsByPriceRange(anyDouble(),
                                                                                                    anyDouble());
            case "createProduct" ->
                    verify(productService, times(1)).createProduct(any(ProductDto.class));
            case "getProductById" ->
                    verify(productService, times(1)).getProductById(anyLong());
            case "deleteProductById" ->
                    verify(productService, times(1)).deleteProductById(anyLong());
            case "updateProduct" ->
                    verify(productService, times(1)).updateProduct(any(ProductDto.class));
            case "updateProductFields" ->
                    verify(productService, times(1)).updateProductFields(any(ProductDto.class));
            default -> throw new IllegalArgumentException("Unsupported method: " + methodName);
        }
    }

}
