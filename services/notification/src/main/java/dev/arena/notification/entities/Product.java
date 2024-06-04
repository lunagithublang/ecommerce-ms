package dev.arena.notification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}
