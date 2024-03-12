package com.microservices.productservice.config;

import com.microservices.productservice.service.mapper.ProductMapper;
import com.microservices.productservice.service.mapper.ProductMapperImpl;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class for the Product module.
 * Loads messages from the "messages.properties" file and configures beans.
 *
 * @author priyanshu
 * @version 1.0
 * @since 31/01/2024
 */
@Getter
@Configuration
@PropertySource("classpath:errorcode.properties")
@PropertySource("classpath:messages.properties")
public class ProductConfig {

    /**
     * Creates and configures the ProductMapper bean.
     *
     * @return The configured ProductMapper bean.
     */
    @Bean
    public ProductMapper productMapper() {
        return new ProductMapperImpl();
    }

}
