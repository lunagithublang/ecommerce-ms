package dev.arena.product.dto.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO (
        @NotNull(message = "Category id is required")
        UUID category_id,
        @NotEmpty(message = "Product name is required")
        String name,
        @NotEmpty(message = "Product description is required")
        String description,
        @NotNull
        @Positive
        int available_quantity,
        @Positive
        @NotNull(message = "Product price is required")
        BigDecimal price

) {
}
