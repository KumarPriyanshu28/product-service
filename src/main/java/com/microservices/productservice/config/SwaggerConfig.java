package com.microservices.productservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation settings in the product-service.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title="product-service",
                version = "1.0.0",
                contact = @Contact(
                        name = "Kumar Priyanshu",
                        email = "kumarpriyanshu2822@gmail.com"),
                license = @License(
                        name = "Terms of Service",
                        url = "")),
        servers= @Server(url = "localhost:8085/products")
)
public class SwaggerConfig {

}
