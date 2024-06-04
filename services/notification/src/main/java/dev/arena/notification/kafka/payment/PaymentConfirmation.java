package dev.arena.notification.kafka.payment;

import dev.arena.notification.enums.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String orderReference,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
