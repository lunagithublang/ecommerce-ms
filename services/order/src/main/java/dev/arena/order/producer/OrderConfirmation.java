package dev.arena.order.producer;

import dev.arena.order.dto.customer.CustomerResponseDTO;
import dev.arena.order.dto.product.PurchaseProductResponseDTO;
import dev.arena.order.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;


public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponseDTO customer,
        List<PurchaseProductResponseDTO> products
) {
}
