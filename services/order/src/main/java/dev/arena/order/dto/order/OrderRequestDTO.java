package dev.arena.order.dto.order;

import dev.arena.order.dto.product.PurchaseProductRequestDTO;
import dev.arena.order.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(
        @NotNull
        @NotBlank
        @NotEmpty
        String customerId,
        String reference,
        @Positive
        BigDecimal totalAmount,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        List<PurchaseProductRequestDTO> products
) {
}
