package com.microservices.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Entity class representing a product.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Data
@Entity
@Table(name = "product", indexes = {@Index(name = "price_index", columnList = "productPrice")})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@DynamicInsert
@DynamicUpdate
public class Product {
    /**
     * Unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    /**
     * Name of the product.
     */
    private String productName;

    /**
     * Price of the product.
     */
    private double productPrice;
}
