package dev.arena.order.dto.orderline;


import dev.arena.order.entities.Order;

import java.util.UUID;

public record OrderLineRequestDTO(
        Order order,
        int quality,
        UUID productId
) {
}
