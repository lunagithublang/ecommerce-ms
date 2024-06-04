package dev.arena.payment.dto;

import dev.arena.payment.entities.Customer;
import dev.arena.payment.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestDTO(
         BigDecimal amount,
         PaymentMethod paymentMethod,
         UUID orderId,
         String orderReference,
         Customer customer
) {
}
