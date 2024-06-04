package dev.arena.notification.kafka.order;

import dev.arena.notification.entities.Customer;
import dev.arena.notification.entities.Product;
import dev.arena.notification.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
){
}

