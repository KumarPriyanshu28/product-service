package com.microservices.productservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception class for ProductService-related errors.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Getter
public class ProductServiceException extends RuntimeException {

    /**
     * The HTTP status associated with the exception.
     */
    private final HttpStatus httpStatus;

    /**
     * Constructs a new ProductServiceException with the specified message and HTTP status.
     *
     * @param message    A description of the exception.
     * @param httpStatus The HTTP status associated with the exception.
     */
    public ProductServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
