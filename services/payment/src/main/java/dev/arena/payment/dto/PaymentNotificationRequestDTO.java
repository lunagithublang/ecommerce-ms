package dev.arena.payment.dto;
import dev.arena.payment.enums.PaymentMethod;

import java.math.BigDecimal;


public record PaymentNotificationRequestDTO(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String orderReference,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
