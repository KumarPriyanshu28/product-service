package com.microservices.productservice.service.mapper;

import com.microservices.productservice.dto.ProductDto;
import com.microservices.productservice.entity.Product;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper interface for populating entities and Dto related to products.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Mapper
public interface ProductMapper {
    /**
     * Maps a ProductDto to a Product entity.
     *
     * @param productDto The Dto containing information for creating a new product.
     * @return The populated Product entity.
     */
    @Mapping(target = "productId", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    /**
     * Maps a Product entity to a ProductDto.
     *
     * @param product The Product entity to be mapped.
     * @return The populated ProductDto.
     */
    ProductDto productToProductDto(Product product);
    /**
     * Maps a list of Product entities to a list of ProductDto.
     *
     * @param productList The list of Product entities to be mapped.
     * @return The populated list of ProductDto.
     */
    List<ProductDto> productListToProductDtoList(List<Product> productList);

    /**
     * Converts a ProductDto object into a Product object.
     *
     * @param product The target Product object to be updated.
     * @param productDto The source ProductDto object.
     * @return The updated Product object.
     */
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
                 nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productPrice" , expression = "java(updatePrice(product, productDto))")
    Product convertProductDtoToProduct(@MappingTarget Product product, ProductDto productDto);

    /**
     * Updates the price based on the ProductDto value or retains the existing price.
     *
     * @param product     The existing Product.
     * @param productDto  The source ProductDto.
     * @return The updated quantity.
     */
    default double updatePrice(Product product, ProductDto productDto) {
        return (productDto.getProductPrice() > 0.0) ? productDto.getProductPrice() : product.getProductPrice();
    }

}

