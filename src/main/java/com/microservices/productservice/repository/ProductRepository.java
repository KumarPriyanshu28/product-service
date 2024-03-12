package com.microservices.productservice.repository;

import com.microservices.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Product entity.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieves all products sorted by price in ascending order.
     *
     * @return A list of products sorted by price.
     */
    @Query("SELECT p FROM Product p ORDER BY productPrice ASC")
    List<Product> findAllSortedByPrice();

    /**
     * Retrieves products within the specified price range.
     *
     * @param lowerLimit The lower limit of the price range.
     * @param upperLimit The upper limit of the price range.
     * @return A list of products within the given price range.
     */
    @Query("SELECT p FROM Product p WHERE productPrice BETWEEN ?1 AND ?2")
    List<Product> findByPriceRange(Double lowerLimit, Double upperLimit);
}
