package dev.arena.order.dto.product;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseProductResponseDTO(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
