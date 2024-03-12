package com.microservices.productservice.controller;

import com.microservices.productservice.dto.ErrorDto;
import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.dto.group.OnCreate;
import com.microservices.productservice.dto.group.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller interface for managing products.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Tag(name = "ProductController", description = "APIs for managing products")
@RequestMapping("/products")
public interface ProductController {

    /**
     * Retrieves all products.
     *
     * @return A ResponseEntity containing a list of ProductDto.
     */
    @Operation(summary = "Retrieve all products.",
            description = "Retrieve all the products from the database.",
            tags = {"GET"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the products.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})
    })
    @GetMapping
    ResponseEntity<List<ProductDto>> getAllProducts();

    /**
     * Retrieves all products sorted by price.
     *
     * @return A ResponseEntity containing a sorted list of ProductDto.
     */
    @Operation(summary = "Retrieve all products sorted by price.",
            description = "Retrieve all the products sorted by price in ascending order from the database.",
            tags = {"GET"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the products sorted by price.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))})
    })
    @GetMapping("/sorted")
    ResponseEntity<List<ProductDto>> getAllProductsSortedByPrice();

    /**
     * Retrieves products within the specified price range.
     *
     * @param lowerLimit The lower limit of the price range.
     * @param upperLimit The upper limit of the price range.
     * @return A ResponseEntity containing a list of ProductDto within the given price range.
     */
    @Operation(summary = "Retrieves products within the specified price range.",
            description = "Retrieve all the products within the specified price range from the database.",
            tags = {"GET"})
    @Parameter(name = "lowerLimit", description = "The lower limit of the price range.")
    @Parameter(name = "upperLimit", description = "The upper limit of the price range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved products within the specified price range.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid price range parameters.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @GetMapping("/price/range/{lowerLimit}/{upperLimit}")
    ResponseEntity<List<ProductDto>> getProductsByPriceRange(@PathVariable Double lowerLimit,
                                                             @PathVariable Double upperLimit);

    /**
     * Creates a new product.
     *
     * @param productDto The Dto containing information for creating a new product.
     * @return A ResponseEntity containing the created ProductDto.
     */
    @Operation(summary = "Creates a new product.",
            description = "Creates a new product in the database.",
            tags = {"POST"})
    @Parameter(name = "productDto", description = "The Dto containing information for creating a new product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully created a new product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input for creating a product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @PostMapping
    ResponseEntity<ProductDto> createProduct(@RequestBody @Validated({OnCreate.class}) ProductDto productDto);

    /**
     * Retrieves a product by its unique identifier.
     *
     * @param productId The unique identifier of the product.
     * @return A ResponseEntity containing the ProductDto for the specified productId.
     */

    @Operation(summary = "Retrieves a product by its Id",
            description = "Retrieves a single product from the database based on its unique identifier.",
            tags = {"GET"})
    @Parameter(name = "productId", description = "The unique identifier of the product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Product not found.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @GetMapping("/{productId}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long productId);

    /**
     * Deletes a product by its unique identifier.
     *
     * @param productId The unique identifier of the product to be deleted.
     * @return A ResponseEntity containing the deleted ProductDto.
     */
    @Operation(summary = "Deletes a product by its Id.",
            description = "Deletes a product from the database based on its unique identifier.",
            tags = {"DELETE"})
    @Parameter(name = "productId", description = "Id of the product to be deleted.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the product.", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found.", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @DeleteMapping("/{productId}")
    ResponseEntity<ProductDto> deleteProductById(@PathVariable Long productId);

    /**
     * Updates a product by its unique identifier.
     *
     * @return A ResponseEntity containing the updated ProductResponseDto.
     */
    @Operation(summary = "Updates a product.",
            description = "Updates a product from the database.",
            tags = {"PUT"})
    @Parameter(name = "productDto", description = "The Dto containing information for updating the product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated the product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input for updating the product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found.", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @PutMapping
    ResponseEntity<ProductDto> updateProduct(@RequestBody @Validated({OnCreate.class}) ProductDto productDto);

    /**
     * Partially updates product fields of a product identified by the given unique identifier.
     *
     * @param productDto The Dto containing information for updating the product.
     * @return A ResponseEntity containing the updated ProductDto.
     */
    @Operation(summary = "Partially updates product fields of a product by its Id.",
            description = "Partially updates product fields of a product identified by the given unique identifier",
            tags = {"PATCH"})
    @Parameter(name = "productDto", description = "The Dto containing information for updating the product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully updated the product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input for updating the product.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found.", content =
                    {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDto.class))})
    })
    @PatchMapping
    ResponseEntity<ProductDto> updateProductFields(@RequestBody @Validated({OnUpdate.class}) ProductDto productDto);

}


