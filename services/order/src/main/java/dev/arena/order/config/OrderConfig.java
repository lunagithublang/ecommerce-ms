package dev.arena.order.config;

import dev.arena.order.mappers.CustomerMapper;
import dev.arena.order.mappers.OrderLineMapper;
import dev.arena.order.mappers.OrderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public OrderMapper orderMapper() {
        return OrderMapper.INSTANCE;
    }

    @Bean
    public CustomerMapper customerMapper() {
        return CustomerMapper.INSTANCE;
    }

    @Bean
    public OrderLineMapper orderLineMapper() {
        return OrderLineMapper.INSTANCE;
    }
}
