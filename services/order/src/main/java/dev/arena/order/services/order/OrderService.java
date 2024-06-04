package dev.arena.order.services.order;

import dev.arena.common.dto.PageResponseDTO;
import dev.arena.order.dto.order.OrderRequestDTO;
import dev.arena.order.dto.order.OrderResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface OrderService {
    OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO);

    Boolean deletedById(UUID resourceId);

    PageResponseDTO<OrderResponseDTO> findAll(int page, int size, UriComponentsBuilder uriComponentsBuilder);
}
