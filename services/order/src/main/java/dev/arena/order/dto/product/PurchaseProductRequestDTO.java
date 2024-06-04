package dev.arena.order.dto.product;

import dev.arena.order.validations.product.validate_product_id.ValidateProductId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record PurchaseProductRequestDTO(
        @Positive
        @NotEmpty
        int quantity,
        @NotNull
        @ValidateProductId
        UUID productId
) {
}
