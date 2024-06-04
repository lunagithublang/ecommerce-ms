package dev.arena.order.dto.payment;

import dev.arena.order.dto.customer.CustomerResponseDTO;
import dev.arena.order.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestDTO(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        CustomerResponseDTO customer
) {
}
