package dev.arena.payment.config;

import dev.arena.payment.mappers.PaymentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public PaymentMapper paymentMapper() {
        return PaymentMapper.INSTANCE;
    }
}
