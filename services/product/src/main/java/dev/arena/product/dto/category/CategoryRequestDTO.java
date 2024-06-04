package dev.arena.product.dto.category;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequestDTO(
         @NotEmpty
         String name,
         @NotEmpty
         String description
) {
}
