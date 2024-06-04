package dev.arena.product.config;

import dev.arena.product.mappers.category.CategoryMapper;
import dev.arena.product.mappers.product.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductMapper productMapper() {
        return ProductMapper.INSTANCE;
    }

    @Bean
    public CategoryMapper categoryMapper() {
        return CategoryMapper.INSTANCE;
    }
}
