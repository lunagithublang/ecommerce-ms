package dev.arena.customer.config;


import dev.arena.customer.mappers.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class CustomerConfig {



    @Bean
    public CustomerMapper customerMapper() {
        return CustomerMapper.INSTANCE;
    }


}


