package dev.arena.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private UUID id;
    private String name;
    private String description;
    private int availableQuantity;
    private BigDecimal price;
}
