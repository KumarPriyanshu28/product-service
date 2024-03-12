package com.microservices.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing error details.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    /**
     * HTTP status code indicating the error.
     */
    private Integer statusCode;

    /**
     * Error message providing additional details about the error.
     */
    private String message;

    /**
     * Timestamp indicating when the error occurred.
     */
    private LocalDateTime timestamp;

}
